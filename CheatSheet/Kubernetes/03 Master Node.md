![[Pasted image 20260531123833.png]]
![[Pasted image 20260531123848.png]]
![[Pasted image 20260531123856.png]]

## **kube-apiserver**

- REST interface
- Save state to the datastore (etcd)
- All clients interact with it, never directly to the datastore.

## etcd

- Act as the cluster datastore for storing state
- Key-value store
- Not a database or a datastore for applications to use
- The single source of truth

## **kube-control-manager**

- The controller of controllers!
- It runs controllers:
    - Node controller
    - Replication controller
    - Endpoints controller
    - Service account & Token controllers

## **cloud-control-manager**

- Interact with the cloud providers controllers
    - **Node**
        - For checking the cloud provider to determine if a node has been deleted in the cloud after it stops responding
    - **Route**
        - For setting up routes in the underlying cloud infrastructure
    - **Service**
        - For creating, updating and deleting cloud provider load balancers
    - **Volume**
        - For creating, attaching, and mounting volumes, and interacting with the cloud provider to orchestrate volumes

## **Addons**

- DNS
- Web UI (dashboard)
- Cluster-level logging
- Container resource monitoring