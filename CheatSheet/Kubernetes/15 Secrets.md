![[Pasted image 20260603223217.png]]
![[Pasted image 20260603223235.png]]
![[Pasted image 20260603223328.png]]
![[Pasted image 20260603223337.png]]

# Secrets

- Stored as base64 encoded strings
- Not secure as base64 strings are not encrypted

- Should you use secrets?
- Protect them using RBAC authorization policies
- Store secrets elsewhere
    - Cloud providers offer ways to secure these secrets
        - Azure Key Vault
        - AWS Key Management Service
        - Google Cloud KMS
    - HarshiCorp Vault

![[Pasted image 20260603223641.png]]
![[Pasted image 20260603223733.png]]
![[Pasted image 20260603223743.png]]

# kubectl - Secrets Cheat Sheet

|Command|Description|
|---|---|
|`kubectl create secret generic [secretName] --from-literal=STATE=Michigan`|The imperative way|
|`kubectl apply -f [secret.yaml]`|The declarative way|
|`kubectl get secrets`|List the Secrets|
|`kubectl get secrets [secretName] -o YAML`|Save a Secret in a YAML file _(Note: Capitalized "YAML" in the description, lowercase in command)_|
|`kubectl delete -f [secret.yaml]`|Delete a secret|
|`kubectl delete secrets [secretName]`|Delete a secret|

---

To quickly encode/decode strings into base64

```
https://www.base64encode.org/
https://www.base64decode.org/
```

or on Windows, install base64

```
choco install base64
```

on Linux/Mac

```
echo [string] | base64
echo [encodedString] | base64 -d
```

## Create the Secrets

```
kubectl apply -f secrets.yaml
```

secrets.yaml
```yml
apiVersion: v1
kind: Secret
metadata:
  name: secrets
type: Opaque
data:
  username: VGhlVXNlck5hbWU=
  password: bXlwYXNzd29yZA==
```
## Look at the secrets

```
kubectl get secret
kubectl describe secret secrets
kubectl get secret secrets -o YAML
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
      - name: USERNAME
        valueFrom:
          secretKeyRef:
            name: secrets
            key: username
      - name: PASSWORD
        valueFrom:
          secretKeyRef:
            name: secrets
            key: password            
```
## Connect to the Busybox

```
kubectl exec mybox -it -- /bin/sh
```

## Display the USERNAME and PASSWORD env variables

```
echo $USERNAME
echo $PASSWORD
exit
```

## Cleanup

```
kubectl delete -f secrets.yaml
kubectl delete -f pod.yaml --force --grace-period=0
```