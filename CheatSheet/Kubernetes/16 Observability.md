![[Pasted image 20260603224415.png]]


# Observability - Probes

- Startup probes
    - To know when a container has started
- Readiness probes
    - To know when a container is ready to accept traffic
    - A failing readiness probe will stop the application from receiving traffic
- Liveness probes
    - Indicates whether the code is running or not
    - A failing liveness probe will restart the container

![[Pasted image 20260603224547.png]]
![[Pasted image 20260603224605.png]]
![[Pasted image 20260603224611.png]]
![[Pasted image 20260603224640.png]]

# Probing the container

- The kubelet checks periodically the containers using probes
- ExecAction
    - Execute a command inside the container
- TCPSockectAction _(Note: Spelled "TCPSockectAction" instead of "TCPSocketAction" in the image)_
    - Check if a TCP socket port is open
- HTTPGetAction
    - Performs an HTTP GET against a specific port and path

![[Pasted image 20260603224829.png]]
![[Pasted image 20260603224843.png]]
![[Pasted image 20260603224847.png]]

---
## Deploy the pod

```
kubectl apply -f pod.yaml
```

pod.yaml
```yml
apiVersion: v1
kind: Pod
metadata:
  labels:
    test: liveness
  name: liveness-example
spec:
  containers:
  - name: liveness
    image: busybox
    resources:
      requests:
        cpu: 100m
        memory: 128Mi
      limits:
        cpu: 250m
        memory: 256Mi    
    args:
    - /bin/sh
    - -c
    - touch /tmp/healthy; sleep 15; rm -rf /tmp/healthy; sleep 3600
    livenessProbe:
      exec:
        command:
        - cat
        - /tmp/healthy
      initialDelaySeconds: 5
      periodSeconds: 5
      failureThreshold: 2
```
## Look at the pod events

```
kubectl describe pod liveness-example
```

## Cleanup

```
kubectl delete -f pod.yaml --force --grace-period=0
```