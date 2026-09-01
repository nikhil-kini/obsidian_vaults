**Namespaces**

- Allow to group resources
    - Ex: Dev, Test, Prod
- K8s creates a default workspace
- Objects in one namespace can access objects in a different one
    - Ex: `objectname.prod.svc.cluster.local`
- Deleting a namespace will delete all its child objects

**Namespaces**

- You define a namespace
- You specify the namespace when defining objects

### Namespace definition

```
apiVersion: v1
kind: Namespace
metadata:
  name: prod
```

_Namespace `prod` is defined._

### Pod definition

```
apiVersion: v1
kind: Pod
metadata:
  name: myapp-pod
  namespace: prod
spec:
  containers:
    - name: nginx-container
      image: nginx
```

_The pod will live in the `prod` namespace._

### NetworkPolicy definition

```
kind: NetworkPolicy
apiVersion: networking.k8s.io/v1
metadata:
  namespace: clientb
  name: deny-from-other-namespaces
spec:
  podSelector:
    matchLabels:
  ingress:
    - from:
        - podSelector: {}
```

### ResourceQuota definition

```
apiVersion: v1
kind: ResourceQuota
metadata:
  name: compute-quota
  namespace: prod
spec:
  hard:
    pods: "10"
    limits.cpu: "5"
    limits.memory: 10Gi
```

**Key points:**

- The **NetworkPolicy** is created in the `clientb` namespace and applies to pods within that namespace.
- The **ResourceQuota** is created in the `prod` namespace and limits resources to:
    - Maximum **10 pods**
    - Total CPU limits of **5 cores**
    - Total memory limits of **10 GiB**


## Get namespaces

Open a terminal and get the currently configured namespaces.

```
kubectl get namespaces
kubectl get ns
```

## Get the pods list

Get a list of all the installed pods.

```
kubectl get pods
```

You get the pods from the default namespace. Try getting the pods from the docker namespace. You will get a different list.

```
kubectl get pods --namespace=kube-system
kubectl get pods -n kube-system
```

## Change namespace

Change the namespace to the docker one and get the pods list.

```
kubectl config set-context --current --namespace=kube-system
```

## Get the pods

``` sh
kubectl get pods
kubectl get pods --all-namespaces # list all pods in all the namespaces
```

## Now change back to the default namespace

```
kubectl config set-context --current --namespace=default
kubectl get pods
```

## Create and delete a namespace

```sh 
kubectl create ns [name]
kubectl get ns
kubectl delete ns [name] # Note all the resource under the namespce will be deleted
```

