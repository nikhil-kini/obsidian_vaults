![[Pasted image 20260530120916.png]]
![[Pasted image 20260530121256.png|354]]
## What K8s can do

- Service discovery and load balancing
- Storage orchestration
    - Local or Cloud based
- Automated rollouts and rollbacks
- Self-healing
- Secret and configuration management
- Use the same API across on-premise and every cloud providers

## Installation

Both **Minikube** and **Kind** are tools to run a **local Kubernetes cluster on your own machine** for development, testing, and learning.
For multi-node infrastructure, deploying a standard cluster using [kubeadm](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/) is the industry standard.

Refer this to install Kind. https://kind.sigs.k8s.io/docs/user/quick-start/

To check install
```sh
kind version
```

To create a cluster with name `dev`
```
kind create cluster --name dev
```

### What problem do they solve?

`kubectl` is only a **client tool**. It talks to a Kubernetes cluster.

To install **Kubectl** https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/

### To test local installation

```sh
kubectl cluster-info
```


## Communication with K8s

![[Pasted image 20260531103002.png]]

### CLI
- `kubectl` is the CLI.
- Communicates with the apiserver
- Configuration stored locally
    - `${HOME}/.kube/config`
    - `C:\Users\{USER}\.kube\config`

# K8s Context

- A context is a group of access parameters to a K8s cluster
- Contains a Kubernetes cluster, a user, and a namespace
- The current context is the cluster that is currently the default for kubectl
    - All kubectl commands run against that cluster

### Example

A context combines:
```yml
clusters:
  - name: dev-cluster

users:
  - name: developer

contexts:
  - name: dev
    context:
      cluster: dev-cluster
      user: developer
      namespace: default

current-context: dev
```

When you run:
```
kubectl get pods
```

`kubectl` looks at `current-context: dev`, finds:

- Cluster → `dev-cluster`
- User → `developer`
- Namespace → `default`

and sends the request to that cluster using that user's credentials.

# kubctl - Context Cheat Sheet

| Command                                       | Description                           |
| --------------------------------------------- | ------------------------------------- |
| `kubectl config current-context`              | Get the current context               |
| `kubectl config get-contexts`                 | List all context                      |
| `kubectl config use-context [contextName]`    | Set the current context               |
| `kubectl config delete-context [contextName]` | Delete a context from the config file |

Get the active context:
```
kubectl config current-context
```

List all available contexts:
```
kubectl config get-contexts
```

Switch to another context:
```
kubectl config use-context dev-cluster
```

Delete a context:
```
kubectl config delete-context dev-cluster
```

## Using kubectx

What's great about Kubernetes is the incredible amount of tools created by the community and available for free. Kubectx is a simple tool that provides an easy way to list and change context.

To install refer: https://github.com/ahmetb/kubectx#installation

To list the contexts, simply type:
```
kubectx
```

To change context:
```
kubectx <contextName>
```

## Rename context

Rename context:
```
kubectl config rename-context [old-name] [new-name]
```

## Delete context

Delete context:
```
kubectl config delete-context [contextName]
```


