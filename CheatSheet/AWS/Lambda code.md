Insert Cognito User into Dynamo DB
```js
import { DynamoDB, PutItemCommand } from "@aws-sdk/client-dynamodb";
import {Stripe} from "stripe";

var stripe = new Stripe('rk_test');

var ddb = new DynamoDB();

export const handler = async (event, context) => {
  
  let date = new Date();
    if (event.request.userAttributes.sub) {
        
        const stripeResponse = await stripe.customers.create({
        email: event.request.userAttributes.email,
        name: event.request.userAttributes.name,
        metadata: {
            userId : event.request.userAttributes.sub
        }
    });
        
        let params = {
            Item: {
                'userId': {S: event.request.userAttributes.sub},
                'username': {S: event.userName},
                'Email': { S: event.request.userAttributes.email },
                'createdAt': {S: date.toISOString()},
                "stripeId" : {S: stripeResponse.id}
            },
            TableName: process.env.UserPaymenTable
        };
        
        const command = new PutItemCommand(params);

        try {
            const response = await ddb.send(command);
            console.log("Success");
        } catch (err) {
            console.log("Error", err);
        }

        console.log("Success: Everything executed correctly");
        context.done(null, event);

    } else {
        console.log("Error: Nothing was written to DynamoDB");
        context.done(null, event);
    }
  
};


```

stripe server
```js
import {Stripe} from "stripe";

var stripe = new Stripe('sk_te');

const YOUR_DOMAIN = 'http://localhost:4200';
const headers = {
    "Content-Type": "application/json",
  };
let statusCode = 200;
let body;

export const handler = async (event, context, callback) => {
    console.log(event);
    
    const session = await stripe.checkout.sessions.create({
    line_items: [
      {
        // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
        price: 'price_1OGDwJSCNIJH36kwSmCCZECe',
        quantity: 1,
      },
    ],
    customer: JSON.parse(event.body).cus_id,
    metadata: {
      user_id : JSON.parse(event.body).user_id
    },
    mode: 'subscription',
    success_url: `${YOUR_DOMAIN}/success`,
    cancel_url: `${YOUR_DOMAIN}/cancel`,
  });
  
body = JSON.stringify(session.url);

        

return {
  statusCode,
  headers,
  body
};

};
```

Read Client Info
```js
import { DynamoDBClient } from "@aws-sdk/client-dynamodb";
import {
  DynamoDBDocumentClient,
  GetCommand
} from "@aws-sdk/lib-dynamodb";

const client = new DynamoDBClient({});

const dynamo = DynamoDBDocumentClient.from(client);

const tableName = process.env.UserPaymenTable;

export const handler = async (event, context) => {
  let body;
  let statusCode = 200;
  const headers = {
    "Content-Type": "application/json",
  };
  
  // console.log(event);

  try {
    switch (event.requestContext.resourceId) {
      case "GET /read-client/{id}":
        body = await dynamo.send(
          new GetCommand({
            TableName: tableName,
            Key: {
              "userId": event.pathParameters.id,
            },
          })
        );
        body = body.Item;
        break;
      default:
        throw new Error(`Unsupported route: "${event.routeKey}"`);
    }
  } catch (err) {
    statusCode = 400;
    body = err.message;
  } finally {
    body = JSON.stringify(body);
  }

  return {
    statusCode,
    headers,
    body
  };
};


```

stripe webhook
```js
import {Stripe} from "stripe";

var stripe = new Stripe('sk_test_5');

const endpointSecret = 'we_1OGMS';

export const handler = async (event) => {
  // TODO implement
  const response = {
    statusCode: 200,
    body: JSON.stringify('Hello from Lambda!'),
  };
  
  console.log(event);
  
  const payload = JSON.parse(event.body);
  const sig = event.headers['stripe-signature'];

  let stripe_event;

  try {
    stripe_event = stripe.webhooks.constructEvent(payload, sig, endpointSecret);
    console.log("Stripe Verification Sucess");
  } catch (err) {
     console.log(" FAIL Stripe verify");
  }
  
  console.log(stripe_event);
  
  return response;
};

```

cloud insight
```
fields @message

| parse @message "* *" as loggingType, loggingMessage

| filter loggingType != "END" and loggingType != "REPORT" and loggingType != "START" and loggingType != "INIT_START"

| sort @timestamp desc

| display @timestamp, @message
```