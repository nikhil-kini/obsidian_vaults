
# The declarative way vs the imperative way

### Imperative

- Using kubectl commands, issue a series of commands to create resources
- Great for learning, testing and troubleshooting
- It's like code

### Declarative

- Using kubectl and YAML manifests defining the resources that you need
- Reproducible, repeatable
- Can be saved in source control
- It's like data that can be parsed and modified

## Why declarative is preferred in production

For a Spring Boot microservice, you'd typically store:

```
k8s/  
├── deployment.yaml  
├── service.yaml  
├── ingress.yaml  
└── configmap.yaml
```

in Git and deploy with:

```
kubectl apply -f k8s/
```

Benefits:
- Version controlled
- Repeatable deployments
- Easy rollbacks
- Works with CI/CD pipelines
- Compatible with GitOps tools like Argo CD and Flux

## Imperative

```
kubectl create deployment mynginx1 --image=nginx
```

To get the deployment
```
kubectl get deployment
```
## Declarative

```
kubectl create -f deploy-example.yaml
```

deploy-example.yaml
```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mynginx2
spec:
  replicas: 1
  selector:
    matchLabels:
      app: nginx
      env: prod
  template:
    metadata:
      labels:
        app: nginx
        env: prod
    spec:
      containers:
      - name: nginx
        image: nginx
        resources:
          requests:
            cpu: 100m
            memory: 128Mi
          limits:
            cpu: 250m
            memory: 256Mi        
        ports:
        - containerPort: 80
```

## Cleanup

```
kubectl delete deployment mynginx1
kubectl delete deploy mynginx2
```