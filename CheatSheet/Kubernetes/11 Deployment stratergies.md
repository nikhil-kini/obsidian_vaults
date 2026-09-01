## 1. Recreate Strategy

The old version is completely terminated before the new version starts.

```
v1 Pods Running
     ↓
Terminate v1
     ↓
Start v2
```

### Configuration

```
strategy:
  type: Recreate
```

### Pros

- Simple
- No version compatibility issues

### Cons

- Downtime occurs
- Service unavailable during deployment

### Use Cases

- Internal applications
- Applications that cannot run multiple versions simultaneously
- Major schema changes

![[Pasted image 20260531175036.png]]

---

## 2. Rolling Update (Default)

Gradually replaces old Pods with new Pods.

```
Initial:
v1 v1 v1 v1

Step 1:
v2 v1 v1 v1

Step 2:
v2 v2 v1 v1

Step 3:
v2 v2 v2 v1

Final:
v2 v2 v2 v2
```

### Configuration

```
strategy:
  type: RollingUpdate
  rollingUpdate:
    maxUnavailable: 1
    maxSurge: 1
```

### Parameters

#### maxUnavailable

Maximum number of Pods allowed to be unavailable.

```
maxUnavailable: 1
```

For 10 replicas:

```
Running: 9
Unavailable: 1
```

---

#### maxSurge

Extra Pods allowed during deployment.

```
maxSurge: 2
```

For 10 replicas:

```
Desired: 10
Actual: 12
```

### Example

Deployment has:

```
replicas: 4
maxUnavailable: 1
maxSurge: 1
```

Process:

```
4 old pods

↓ create 1 new pod

4 old + 1 new

↓ remove 1 old

3 old + 1 new

↓ repeat
```

- maxSurge
    - Maximum number of Pods that can be created over the desired number of Pods
    - Value or percentage
- maxUnavailable
    - Maximum number of Pods that can be unavailable during the update process
- **Default strategy with maxSurge and maxUnavailable both set to 25%**

### Pros

- Zero or minimal downtime
- Native Kubernetes support
- Easy rollback

### Cons

- Requires application backward compatibility
- Can temporarily consume more resources

### Most commonly used strategy in production

![[Pasted image 20260531175105.png]]

## kubectl - RollingUpdate Cheat Sheet

|Command|Description|
|---|---|
|`kubectl apply -f [definition.yaml]`|Update a deployment|
|`kubectl rollout status`|Get the progress of the update|
|`kubectl rollout history deployment [deploymentname]`|Get the history of the deployment|
|`kubectl rollout undo [deploymentname]`|Rollback a deployment|
|`kubectl rollout undo [deploymentname] --to-revision=[revision#]`|Rollback to a revision number|

---
## Create a V1 Deployment

```
kubectl create -f hello-deployment.yaml
```

hello-deployment.yam
```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: hello-dep
  namespace: default
spec:
  replicas: 3
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 1
  selector:
    matchLabels:
      app: hello-dep
  template:
    metadata:
      labels:
        app: hello-dep
    spec:
      containers:
      - image: guybarrette/hello-app:1.0
        resources:
          requests:
            cpu: 100m
            memory: 128Mi
          limits:
            cpu: 250m
            memory: 256Mi      
        imagePullPolicy: Always
        name: hello-dep
        ports:
        - containerPort: 8080
```
## Get the deployment status

```
kubectl rollout status deployment/hello-dep
```

## Get the pods list

```
kubectl get pods -o wide
```

## Describe the pod

```
kubectl describe pod hello-dep
```

## How many ReplicaSets do we have?

```
kubectl get rs
```

Do not delete the Deployment yet!

---
## Create a V2 Deployment

Edit the YAML file and change the container version from 1.0 to 2.0. Save the file.

## Create the Deployment

```
kubectl apply -f hello-deployment.yaml
```

## Get the deployment status

```
kubectl rollout status deployment/hello-dep
```

## Get the pods list

```
kubectl get pods -o wide
```

## How many ReplicaSets do we have?

```
kubectl get rs
```

## Get the deployment history

```
kubectl rollout status deployment/hello-dep
```

---

## Rollback

## Undo the last deployment using either

```
kubectl rollout undo deployment/hello-dep
```

or

```
kubectl rollout undo deployment/hello-dep --to-revision 1
```

## Get the deployment history

```
kubectl rollout status deployment/hello-dep
```

## How many ReplicaSets do we have?

```
kubectl get rs
```

## Cleanup

```
kubectl delete -f hello-deployment.yaml
```

---

## 3. Blue-Green Deployment

Maintains two complete environments.

```
Blue  = Current Production
Green = New Version
```

Before switch:

```
Users  
|
Blue(v1)
```

After switch:

```
Users  
|
Green(v2)
```

### Implementation

Usually done using:

- Service selector switch
- Ingress switch
- Service Mesh

Example:

```
selector:  version: green
```

### Pros

- Instant rollback
- Near-zero downtime
- Full testing before release

### Cons

- Doubles infrastructure requirements
- More complex

### Common for

- Banking
- E-commerce
- High-availability systems

---

## Create a V1 Deployment

```
kubectl create -f hello-dep-v1.yaml
```

```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: hello-v1
spec:
  replicas: 3
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 1
  selector:
    matchLabels:
      app: hello-v1
  template:
    metadata:
      labels:
        app: hello-v1
    spec:
      containers:
      - image: guybarrette/hello-app:1.0
        resources:
          requests:
            cpu: 100m
            memory: 128Mi
          limits:
            cpu: 250m
            memory: 256Mi      
        imagePullPolicy: Always
        name: hello-v1
        ports:
        - containerPort: 8080
```
## Create the ClusterIP service

```
kubectl create -f clusterip.yaml
```

clusterip.yaml
```yml
apiVersion: v1
kind: Service
metadata:
 name: svc-front
spec:
  ports:
  - port: 8080
    targetPort: 8080
  selector:
    app: hello-v1
```

## Get the pods list

```
kubectl get pods -o wide
```

## Display the app in a browser

First, port forward to the ClusterIP:

```
kubectl port-forward service/svc-front 8080:8080
```

Open a browser and navigate to [http://localhost:8080](http://localhost:8080)

The app version will be V1.

---

## Create a V2 Deployment


```
kubectl create -f hello-dep-v2.yaml
```

hello-dep-v2.yaml
```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: hello-v2
spec:
  replicas: 3
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 1
  selector:
    matchLabels:
      app: hello-v2
  template:
    metadata:
      labels:
        app: hello-v2
    spec:
      containers:
      - image: guybarrette/hello-app:2.0
        resources:
          requests:
            cpu: 100m
            memory: 128Mi
          limits:
            cpu: 250m
            memory: 256Mi      
        imagePullPolicy: Always
        name: hello-v2
        ports:
        - containerPort: 8080
```
## Get the pods list

```
kubectl get pods -o wide
```

## Edit the ClusterIP manifest

Edit the clusterip.yaml file and change the last line so that the service points to our V2 deployment.

```
app: hello-v2
```

## Update the ClusterIP service

```
kubectl apply -f clusterip.yaml
```

## Display the app in a browser

First, port forward to the ClusterIP:

```
kubectl port-forward service/svc-front 8080:8080
```

Open a browser and navigate to [http://localhost:8080](http://localhost:8080)

The app version will be V2.

## Cleanup

```
kubectl delete -f hello-dep-v1.yaml
kubectl delete -f hello-dep-v2.yaml
kubectl delete -f clusterip.yaml
```


---

## 4. Canary Deployment

Deploy new version to a small subset of users first.

```
90% → v1
10% → v2
```

Then:

```
75% → v1
25% → v2
```

Eventually:

```
0% → v1
100% → v2
```

### Implementation

Typically done using:

- Istio
- Linkerd
- NGINX Ingress Controller
- Argo Rollouts

### Pros

- Low risk
- Real user testing
- Easy monitoring

### Cons

- More complex routing
- Requires traffic management

---

## 5. A/B Testing

Users are routed based on characteristics.

```
Mobile Users  → v1
Desktop Users → v2
```

or

```
India → v1
US    → v2
```

Typically implemented using:

- Service Mesh
- Advanced Ingress rules

### Use Cases

- Feature experiments
- Product optimization

---

## 6. Shadow (Mirrored) Deployment

Production traffic is copied to the new version.

```
User Request
      |
      ├── v1 (response returned)
      |
      └── v2 (response discarded)
```

The user only sees v1 responses.

### Pros

- Safest way to test
- Real production traffic

### Cons

- Higher resource usage
- More complex

---

## Comparison

|Strategy|Downtime|Rollback|Cost|Complexity|
|---|---|---|---|---|
|Recreate|High|Easy|Low|Low|
|Rolling Update|None/Minimal|Easy|Low|Low|
|Blue-Green|None|Instant|High|Medium|
|Canary|None|Easy|Medium|High|
|A/B Testing|None|Easy|Medium|High|
|Shadow|None|Easy|High|Very High|

## Interview Question

**Which deployment strategy does Kubernetes support natively?**

**Answer:** Only **Recreate** and **RollingUpdate** are native Deployment strategies.

```
strategy:  type: Recreate
```

```
strategy:  type: RollingUpdate
```

**Blue-Green, Canary, A/B Testing, and Shadow deployments require additional tooling** such as Argo Rollouts, Istio, or ingress/service-routing mechanisms.


