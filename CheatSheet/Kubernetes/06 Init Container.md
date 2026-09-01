# Init Container

- Database
- API
- Files

My app has a dependency on \[insert here] but I don't want to clutter it with infrastructure code

![[Pasted image 20260531144308.png]]

### Init Containers

- Always run to completion
- Each init container must complete successfully before the next one starts
- If it fails, the kubelet repeatedly restarts it until it succeeds
    - Unless it's restartPolicy is set to Never
- Probes are not supported
    - livenessProbe, readinessProbe, or startupProbe

---

![[Pasted image 20260531144440.png]]
 - `init-myservice` will run 1st
 - `init-mydb` will run 2nd
 - finally `myapp-container`

---

## Create the deployment

```
kubectl apply -f myapp.yaml
```

myapp.yaml
```yml
apiVersion: v1
kind: Pod
metadata:
  name: init-demo
spec:
  containers:
  - name: nginx
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
    volumeMounts:
    - name: workdir
      mountPath: /usr/share/nginx/html
  initContainers:
  - name: install
    image: busybox
    command:
    - wget
    - "-O"
    - "/work-dir/index.html"
    - http://info.cern.ch
    volumeMounts:
    - name: workdir
      mountPath: "/work-dir"
  volumes:
  - name: workdir
    emptyDir: {}
```

Wait for the main pod to come up

```
kubectl get pods
```

## Connect to the Nginx container


```
kubectl exec -it init-demo -- /bin/bash
```

## Hit the default webpage


It should be the one downloaded by the Init container from [http://info.cern.ch](http://info.cern.ch)

```
curl localhost
exit
```

## Cleanup

```
kubectl delete -f myapp.yaml
```