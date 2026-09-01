Services

- A service is another type of K8s object
- Pod IPs are unreliable but service IPs are
- Durable (unlike pods)
    - Static IP address
    - Static DNS name
    - `[servicename].[namespace].svc.cluster.local`
- Services are ways to access pods
- Target pods using selectors

![[Pasted image 20260601215143.png]]
![[Pasted image 20260601215206.png]]

![[Pasted image 20260601215214.png]]

# ClusterIP

- ClusterIP is the default service
- Visibility
    - Cluster internal
- Port
    - The port the service is listening to
- TargetPort
    - Redirecting to the port the pod(s) are listening to
- Load balanced using round robin
    - Session affinity is configurable
- When to use
    - To provide a durable way to communicate with pods inside the cluster

```yml
apiVersion: v1
kind: Service    # marker for cluster IP
metadata:
  name: svc-example
spec:
  ports:
    - port: 80
      targetPort: 8080
  selector:
    app: app-example
    env: prod
```

![[Pasted image 20260601215509.png]]

![[Pasted image 20260601215527.png]]
![[Pasted image 20260601215549.png]]
![[Pasted image 20260601215606.png]]


## kubectl - ClusterIP Cheat Sheet

|Command|Description|
|---|---|
|`kubectl expose po [podName] --port=80 --target-port=8080 --name=frontend`|Create a service to expose a pod|
|`kubectl expose deploy [deployName] --port=80 --target-port=8080`|Create a service to expose a deployment|
|`kubectl apply -f [definition.yaml]`|Deploy the service|
|`kubectl get svc`|Get the services list|
|`kubectl get svc -o wide`|Get extra info|
|`kubectl describe svc [serviceName]`|Describe the service|
|`kubectl delete -f [definition.yaml]`|Delete the service|
|`kubectl delete svc [serviceName]`|Delete the service using it's name|

---
## Deploy the service

```
kubectl apply -f clusterip.yaml
```

clusterip.yaml
```yml
apiVersion: v1
kind: Service
metadata:
 name: svc-example
spec:
  ports:
  - port: 8080
    targetPort: 80
  selector:
    app: app-example
    env: prod
```
## Deploy the app

```
kubectl apply -f deploy-app.yaml
```

```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: deploy-example
spec:
  replicas: 3
  revisionHistoryLimit: 3
  selector:
    matchLabels:
      app: app-example
      env: prod
  template:
    metadata:
      labels:
        app: app-example
        env: prod
    spec:
      containers:
      - name: nginx
        image: nginx:alpine
        ports:
        - containerPort: 80
```
## Deploy Busybox

```
kubectl apply -f pod.yaml
```

```yml
apiVersion: v1
kind: Pod
metadata:
  name: mybox
spec:
  restartPolicy: Always
  containers:
  - name: mybox
    image: busybox
    resources:
      requests:
        cpu: 100m
        memory: 128Mi
      limits:
        cpu: 250m
        memory: 256Mi    
    command:
      - sleep
      - "3600"
```
## Get the pods list

```
kubectl get pods -o wide
```

## Connect to the BusyBox container

```
kubectl exec mybox -it -- /bin/sh
```

## Get the Nginx home page thru the ClusterIP service

```
wget -qO- http://svc-example:8080
exit
```

## Cleanup

```
kubectl delete -f clusterip.yaml
kubectl delete -f deploy-app.yaml
kubectl delete -f pod.yaml --grace-period=0 --force
```

---


# NodePort

- NodePort extend the ClusterIP service _(Note: Written as "extend" instead of "extends" in the image)_
- Visibility
    - Internal and external
- NodePort
    - The port the service is listening to externally
    - Statically defined, or dynamically taken from a range between 30000-32767
- Port
    - The port the service is listening to internally
- TargetPort
    - Redirecting to the port the pod(s) are listening to

Service definition
```yml
apiVersion: v1
kind: Service
metadata:
  name: svc-example
spec:
  type: NodePort  # here is the marker to set type node port
  selector:
    app: nginx
    env: prod
  ports:
    - nodePort: 32410
      protocol: TCP
      port: 80
      targetPort: 80
```

![[Pasted image 20260601220741.png]]

## kubectl - NodePort Cheat Sheet

|Command|Description|
|---|---|
|`kubectl expose po [podName] --port=80 --target-port=8080 --type=NodePort`|Create a service to expose a pod|
|`kubectl expose deploy [deployName] --port=80 --target-port=8080 --type=NodePort --name=frontend`|Create a service to expose a deployment|
|`kubectl apply -f [definition.yaml]`|Deploy the service|
|`kubectl get svc`|Get the services list|
|`kubectl get svc -o wide`|Get extra info|
|`kubectl describe svc [serviceName]`|Describe the service|
|`kubectl delete -f [definition.yaml]`|Delete the service|
|`kubectl delete svc [serviceName]`|Delete the service using it's name|


---

## Deploy the app

```
kubectl apply -f deploy-app.yaml
```

deploy-app.yaml
```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: deploy-nginx
  labels:
    app: nginx
    env: prod
spec:
  replicas: 2
  revisionHistoryLimit: 3
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
        image: nginx:alpine
        ports:
        - containerPort: 80
```
## Deploy the NodePort service

```
kubectl apply -f nodeport.yaml
```

```yml
apiVersion: v1
kind: Service
metadata:
  name: svc-example
spec:
  type: NodePort
  selector:
    app: nginx
    env: prod
  ports:
  - nodePort: 32410
    protocol: TCP
    port: 80
    targetPort: 80
```
## Get the pods list

```
kubectl get pods -o wide
```

## Use the nodeport

Since we are using Docker Desktop and that the Docker Desktop node is mapped to localhost, to reach the service you need to use **localhost** + the **nodeport**.

When using a Cloud provider, you would need to get a node IP address instead of localhost.

Get the node public IP address

```
kubectl get nodes -o wide
```

## Cleanup

```
kubectl delete -f nodeport.yaml
kubectl delete -f deploy-app.yaml
```

---


# LoadBalancer

![[Pasted image 20260601222920.png]]

- Layer 4 Load Balancing
    - Operating at the transport level (TCP)
    - Unable to make decisions based on content
    - Simple algorithms such as round-robin routing
- Layer 7 Load Balancing (Ingress)
    - Operates at the application level (HTTP, SMTP, etc)
    - Able to make decisions based on the actual content of each message
    - More intelligent load balancing decisions and content optimizations
        - Routing rules

--- 
## Deploy the app

```
kubectl apply -f deploy-app.yaml
```

deploy-app.yaml
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: deploy-nginx
  labels:
    app: nginx
    env: prod
spec:
  replicas: 2
  revisionHistoryLimit: 3
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
        image: nginx:alpine
        ports:
        - containerPort: 80
```
## Deploy the Load Balancer service

```
kubectl apply -f loadbalancer.yaml
```

loadbalancer.yaml
```yml
apiVersion: v1
kind: Service
metadata:
  name: svc-example
spec:
  type: LoadBalancer
  selector:
    app: nginx
    env: prod
  ports:
  - protocol: TCP
    port: 8080
    targetPort: 80
```
## Get the pods list

```
kubectl get pods -o wide
```

## Use the Load Balancer

Since we are using Docker Desktop and that the Docker Desktop node is mapped to localhost, to reach the service you need to use **localhost**.

When using a Cloud provider, you would need to get a Load Balancer external IP address instead of localhost.

Get the load balancer public IP address. This will output **localhost** on Docker Desktop.

```
kubectl get svc -o wide
```

## Cleanup

```
kubectl delete -f loadbalancer.yaml
kubectl delete -f deploy-app.yaml
```