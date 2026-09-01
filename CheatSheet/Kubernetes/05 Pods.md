## Pods

- Atomic unit of the smallest unit of work of K8s
- Encapsulates an application's container
- Represents a unit of deployment
- Pods can run one or multiple containers
- Containers within a pod share
    - IP address space, mounted volumes
- Containers within a pod can communicate via
    - Localhost, IPC
-  Pods are ephemeral
- Deploying a pod is an atomic operation, it succeed or not
- If a pod fails, it is replaced with a new one with a shiny new IP address
- You don't update a pod, you replace it with an updated version
- You scale by adding more pods, not more containers in a pod

---

![[Pasted image 20260531142207.png]]

![[Pasted image 20260531142228.png]]

## Pod Lifecycle

![[Pasted image 20260531142348.png]]
- All info about creation is written in `etcd` and is the single source of truth.

![[Pasted image 20260531142457.png]]

## Pod state

- **Pending**
    - Accepted but not yet created
- **Running**
    - Bound to a node
- **Succeeded**
    - Exited with status 0
- **Failed**
    - All containers exit and at least one exited with non-zero status
- **Unknow**
    - Communication issues with the pod
- **CrashLoopBackOff**
    - Started, crashed, started again, and then crashed again

---

## kubectl - Pod Cheat Sheet

| Command                                                            | Description                                              |
| ------------------------------------------------------------------ | -------------------------------------------------------- |
| `kubectl create -f [pod-definition.yml]`                           | Create a pod                                             |
| `kubectl run [podname] --image=busybox -- /bin/sh -c "sleep 3600"` | Run a pod (imperative way)                               |
| `kubectl get pods`                                                 | List the running pods                                    |
| `kubectl get pods -o wide`                                         | Same but with more info                                  |
| `kubectl describe pod [podname]`                                   | Show pod info                                            |
| `kubectl get pod [podname] -o yaml > file.yaml`                    | Extract the pod definition in YAML and save it to a file |
| `kubectl exec -it [podname] -- sh`                                 | Interactive mode                                         |
| `kubectl delete -f [pod-definition.yml]`                           | Delete a pod                                             |
| `kubectl delete pod [podname]`                                     | Same using the pod's name                                |

---
## Demo

Let’s first create a node running Nginx by using the imperative way.

## Create the pod

```
kubectl run mynginx --image=nginx
```

## Get a list of running pods

```
kubectl get pods
```

## Get more info

```
kubectl get pods -o wide
kubectl describe pod mynginx
```

## Delete the pod

```
kubectl delete pod mynginx
```

## Create a pod running BusyBox


Let’s now create a node running BusyBox, this time attaching bash to our terminal.

```
kubectl run mybox --image=busybox -it -- /bin/sh
```

List the folders and use command

```
ls
echo -n 'A Secret' | base64
exit
```

## Cleanup


```
kubectl delete pod mybox
```

## Create a pod using the declarative way

Let’s now create a node using a YAML file.

```
kubectl create -f myapp.yaml
```

myapp.yml
```yml
apiVersion: v1
kind: Pod
metadata:
  name: myapp-pod
  labels:
    app: myapp
    type: front-end
spec:
  containers:
  - name: nginx-container
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
      name: http
      protocol: TCP
    env:
    - name: DBCON
      value: myconnectionstring
```

## Get some info


```
kubectl get pods -o wide
kubectl describe pod myapp-pod
```

## Attach our terminal

``` sh 
kubectl exec -it myapp-pod -- bash
```

Print the DBCON environment variable that was set in the YAML file.

```
echo $DBCON
```

```
exit
```

## Cleanup

```
kubectl delete -f myapp.yaml
```

----
