
## ConfigMaps

- Decouple and externalize configuration
- Referenced as environment variables
- Created from
    - Manifests
    - Files
    - Directories (containing one or more file) 
- Static meaning that if you change values, the containers will have to be restarted to get them

![[Pasted image 20260603222730.png]]

![[Pasted image 20260603222738.png]]
![[Pasted image 20260603222749.png]]
![[Pasted image 20260603222822.png]]
![[Pasted image 20260603222831.png]]

## kubectl - ConfigMaps Cheat Sheet

|Command|Description|
|---|---|
|`kubectl create configmap literal-example --from-literal="city=Ann Arbor" --from-literal=state=Michigan`|The imperative way|
|`kubectl apply -f [cf.yaml]`|The declarative way|
|`kubectl create cm [name] --from-file=myconfig.txt`|From a file|
|`kubectl create cm [name] --from-file=config/`|From a folder|
|`kubectl get cm`|List the ConfigMaps|
|`kubectl get cm [name] -o YAML`|Save a ConfigMap in a YAML file _(Note: Capitalized "YAML" in the description, lowercase in command)_|
|`kubectl delete -f [cf.yaml]`|Delete a ConfigMap|

---

## Create the ConfigMap

```
kubectl apply -f cm.yaml
```

cm.yaml
```yml
apiVersion: v1
kind: ConfigMap
metadata:
  name: cm-example
data:
  state: Michigan
  city: Ann Arbor
```
## Get the ConfigMap info

```
kubectl get cm
kubectl describe configmap cm-example
```

Let's output the same information in YAML format

```
kubectl get configmap cm-example -o YAML
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
    env:
      - name: CITY
        valueFrom:
          configMapKeyRef:
            name: cm-example
            key: city
```
## Connect to the Busybox

```
kubectl exec mybox -it -- /bin/sh
```

## Display the CITY env variable


```
echo $CITY
exit
```

## Cleanup

```
kubectl delete -f cm.yaml
kubectl delete -f pod.yaml --grace-period=0 --force
```