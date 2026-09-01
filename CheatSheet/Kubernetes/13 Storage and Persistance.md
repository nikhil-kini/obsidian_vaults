
## Containers are ephemeral and stateless

- You usually don't store data in containers
- Non-persistent data
    - Locally on a writable layer
    - It's the default, just write to the filesystem
    - When containers are destroyed, so the data inside them _(Note: Typo in image; missing "is", i.e., "so is the data")_

# Volumes

- We need to store data outside the container in a Volume
- Volumes let containers store data into external storage systems
- Vendors create plugins for their storage systems according to the Container Storage Interface
- Two ways to create storage
    - Static and dynamic

--- 

![[Pasted image 20260603220123.png]]


# Persistent Volumes and Claims (Static way)

- Persistent Volumes
    - Represent a storage resource
    - Cluster wide
    - Provisioned by an administrator
- Persistent Volume Claim
    - A one-to-one mapping to a persistent volume
- One or more pods can use a Persistent Volume Claim
- Can be consumed by any of the containers within the pod

![[Pasted image 20260603220329.png]]

![[Pasted image 20260603220343.png]]

### Access Modes

- ReadWriteMany
    - The volume can be mounted as read-write by many pods
- ReadOnlyMany
    - The volume can be mounted read-only by many pods
- ReadWriteOnce
    - The volume can be mounted as read-write by a single pod
    - The other pods are in read-only mode
    - The one that has mounted the volume first will be able to write

---

![[Pasted image 20260603220528.png]]
(Note: HostPath is a local pulgin to create PVC for testing, for prod use cloud provided solution.)
![[Pasted image 20260603220657.png]]
![[Pasted image 20260603220804.png]]

# Persistent Volume States

- **Available**
    - A free resource that is not yet bound to a claim
- **Bound**
    - The volume is bound to a claim
- **Released**
    - The claim has been deleted, but the resource is not yet reclaimed by the cluster
- **Failed**
    - The volume has failed its automatic reclamation


## kubectl - Persistent Volumes Cheat Sheet

|Command|Description|
|---|---|
|`kubectl apply -f [definition.yaml]`|Deploy the PVs and PVCs|
|`kubectl get pv`|Get the PV list|
|`kubectl get pvc`|Get the PVC list|
|`kubectl describe pv [pvName]`|Describe the PV|
|`kubectl delete -f [definition.yaml]`|Delete the PVs and PVCs|
|`kubectl delete pv [pvName]`|Delete the pv using it's name _(Note: Written as "using it's name" instead of "its name")_|
|`kubectl delete pvc [pvcName]`|Delete the pvc using it's name _(Note: Written as "using it's name" instead of "its name")_|
## Create the Persistent Volume

```
kubectl apply -f pv.yaml
```

pv.yaml
```yml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: pv001
  labels:
    type: local
spec:
  storageClassName: ssd 
  capacity:
    storage: 10Mi
  volumeMode: Filesystem
  accessModes:
    - ReadWriteOnce
  persistentVolumeReclaimPolicy: Retain
  hostPath:
    path: "/data/"
```
## Look at the pv

```
kubectl get pv
```

## Deploy the claim

```
kubectl apply -f pvc.yaml
```

pvc.yaml
```yml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: myclaim
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 10Mi
  storageClassName: ssd
```
## Look at the pvc

```
kubectl get pvc
```

## Deploy the pod

```
kubectl apply -f pod.yaml
```

pod.yaml
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
    volumeMounts:
      - mountPath: "/demo/"
        name: mypd
  volumes:
    - name: mypd
      persistentVolumeClaim:
        claimName: myclaim
```
## Connect to the Busybox instance

```
kubectl exec mybox -it -- /bin/sh
```

## Create a file

```
cd demo
cat > hello.txt
Hello World
Enter and Ctrl-D
ls
exit
```

## Delete the pod

Let's delete the pod and deploy it again to validate that the file persisted.

```
kubectl delete -f pod.yaml --force --grace-period=0
```

## Deploy the pod again

```
kubectl apply -f pod.yaml
```

## Connect to the Busybox instance

```
kubectl exec mybox -it -- /bin/sh
cd demo
ls
cat hello.txt
exit
```

## Cleanup

```
kubectl delete -f pod.yaml  --force --grace-period=0
kubectl delete -f pvc.yaml
kubectl delete -f pv.yaml
```

---

# StorageClass (dynamic way)

- Describes the "classes" of storage offered by the admin
- An abstraction on top of an external storage resource
- No need to set a capacity
- Eliminates the need for the admin to pre-provision a persistent volume

![[Pasted image 20260603222149.png]]

![[Pasted image 20260603222202.png]]

### Access Modes

- ReadWriteMany
    - The volume can be mounted as read-write by many pods
- ReadOnlyMany
    - The volume can be mounted read-only by many pods
- ReadWriteOnce
    - The volume can be mounted as read-write by a single pod
    - The other pods are in read-only mode
    - The one that has mounted the volume first will be able to write

![[Pasted image 20260603222321.png]]

![[Pasted image 20260603222332.png]]

## kubectl - StorageClass Cheat Sheet

|Command|Description|
|---|---|
|`kubectl apply -f [definition.yaml]`|Deploy the StorageClass or PVC|
|`kubectl get sc`|Get the StorageClass list|
|`kubectl get pvc`|Get the PVC list|
|`kubectl describe sc [className]`|Describe the StorageClass|
|`kubectl delete -f [definition.yaml]`|Delete the SC and PVC|
|`kubectl delete sc [className]`|Delete the SC using it's name _(Note: Written as "using it's name" instead of "its name")_|
|`kubectl delete pvc [pvcName]`|Delete the PVC using it's name _(Note: Written as "using it's name" instead of "its name")_|

---