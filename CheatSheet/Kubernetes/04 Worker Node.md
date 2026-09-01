![[Pasted image 20260531130821.png]]

**kubelet**

- Manage the pods lifecycle
- Ensures that the containers described in the Pod specs are running and healthy


**kube-proxy**

- A network proxy
- Manages network rules on nodes


**Container runtime**
- K8s supports several container runtimes
- Must implement the Kubernetes Container Runtime Interface (CRI)
    - Moby
    - containerd
    - CRI-O
    - rkt
    - Kata
    - Virtlet

![[Pasted image 20260531131352.png]]
Optimized version
![[Pasted image 20260531131402.png]]

- Docker images run as is. It's business as usual!
- What's changed is what you can do inside the cluster
    - You can no longer access the Docker engine inside the cluster
    - Docker commands won't run if you ssh into a node
    - Use `crictl` instead

**Nodes pool**

- A node pool is a group virtual machines, all with the same size
- A cluster can have multiple node pools
    - These pools can host different sizes of VMs
    - Each pool can be autoscaled independently from the other pools
- Docker Desktop is limited to 1 node

---

## Get nodes information

Get a list of all the installed nodes. Using Docker Desktop, there should be only one.

```
kubectl get nodes
```

Get some info about the node.

```
kubectl describe node
```