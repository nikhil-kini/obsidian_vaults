![[Pasted image 20260531153221.png]]

## kubectl - Pod Cheat Sheet

|Command|Description|
|---|---|
|`kubectl create -f [pod-definition.yml]`|Create a pod|
|`kubectl exec -it [podname] -c [containername] -- sh`|Exec into a pod|
|`kubectl logs [podname] -c [containername]`|Get the logs for a container|

## Typical patterns of K8s

## 1. Sidecar Pattern

A helper container runs alongside the main application container in the same Pod.

![[Pasted image 20260531153332.png]]
### Example

- Main container: Java Spring Boot application
- Sidecar container: Log collector (Fluent Bit)

```
+-------------------+
| Pod               |
|                   |
|  App Container    |
|  Sidecar          |
+-------------------+
```

Uses:

- Logging
- Monitoring
- Service mesh proxies (e.g., Envoy Proxy)

---

## 2. Ambassador Pattern

A proxy container acts as an intermediary between the application and external services.

![[Pasted image 20260531153705.png]]
### Example

Application → Ambassador → Database

Instead of the application knowing the database location, it talks to the ambassador.

Benefits:

- Simplifies configuration
- Easier service migration
- Better security control

---

## 3. Adapter Pattern

Transforms data formats between the application and external systems.

![[Pasted image 20260531153652.png]]
### Example

Application logs:

```
2026-05-31 INFO User Login
```

Adapter converts them into JSON before sending to monitoring systems.

Uses:

- Metrics transformation
- Log transformation
- Legacy integration

---

## 4. Deployment Pattern

Used for stateless applications.

### Example

```
Deployment
 ├─ Pod 1
 ├─ Pod 2
 └─ Pod 3
```

Characteristics:

- Rolling updates
- Self-healing
- Horizontal scaling

Typical for:

- APIs
- Web applications
- Microservices

---

## 5. StatefulSet Pattern

Used when applications require stable identities and persistent storage.

### Example

```
StatefulSet 
├─ mysql-0 
├─ mysql-1 
└─ mysql-2
```

Uses:

- Databases
- Kafka brokers
- Elasticsearch clusters

Characteristics:

- Persistent volumes
- Stable pod names
- Ordered startup/shutdown

---

## 6. DaemonSet Pattern

Runs one Pod on every node.

### Example

```
Node1 -> Monitoring Agent
Node2 -> Monitoring Agent
Node3 -> Monitoring Agent
```

Uses:

- Logging agents
- Monitoring agents
- Security scanners

Examples:

- Fluent Bit
- Prometheus Node Exporter

---

## 7. Job Pattern

Runs a task once and exits.

### Example

```
Database 
MigrationBackupData 
Import
```

```
Job
 └─ Pod
     └─ Complete
```

Uses:

- Data migration
- Report generation
- One-time maintenance tasks

---

## 8. CronJob Pattern

Runs jobs on a schedule.

### Example

```
0 0 * * *
```

Daily backup at midnight.

Uses:

- Scheduled backups
- Cleanup scripts
- Batch processing

---

## 9. Operator Pattern

Encodes operational knowledge into software.

Instead of manually managing a complex system:

```
User
  ↓
Operator
  ↓
Database Cluster
```

Examples:

- Strimzi
- Prometheus Operator

Benefits:

- Automated upgrades
- Automated recovery
- Automated scaling

---

## 10. Horizontal Pod Autoscaling (HPA)

Automatically adjusts replicas.

```
CPU 20% → 2 Pods  
CPU 80% → 10 Pods
```

Useful for:

- Traffic spikes
- Cost optimization

---

## 11. Blue-Green Deployment

Two identical environments:

```
Blue -> Current Production  
Green -> New Version
```

Switch traffic when validation succeeds.

Benefits:

- Near-zero downtime
- Easy rollback

---

## 12. Canary Deployment

Release to a small percentage of users first.

```
90% -> v1  
10% -> v2
```

Then gradually increase:

```
75% -> v1  
25% -> v2
```

Benefits:

- Lower risk deployments
- Real-world testing

---

## 13. Ingress Pattern

Single entry point for many services.

```
Internet
    |
 Ingress
 ├── api.company.com
 ├── app.company.com
 └── admin.company.com
```

Examples:

- NGINX Ingress Controller
- Traefik

---

## 14. Service Discovery Pattern

Pods discover services through DNS.

```
user-service.default.svc.cluster.local
```

Applications do not need hardcoded IP addresses.

---

## 15. GitOps Pattern

Git becomes the source of truth.

```
Git Repo  
↓  
ArgoCD  
↓  
Kubernetes Cluster
```

Examples:

- Argo CD
- Flux

Benefits:

- Auditable changes
- Easy rollback
- Automated deployments

---

## Patterns Most Commonly Seen in Production

If you're learning Kubernetes for real-world DevOps or platform engineering roles, focus on:

1. Deployment
2. Service
3. Ingress
4. ConfigMap & Secret usage
5. StatefulSet
6. DaemonSet
7. Job & CronJob
8. HPA
9. Rolling Updates
10. Blue-Green & Canary Deployments
11. GitOps
12. Operators

---

## Create the pod

```
kubectl create -f two-containers.yaml
```

two-container.yaml
```yml
apiVersion: v1
kind: Pod
metadata:
  name: two-containers
spec:
  restartPolicy: Always
  containers:
  - name: mynginx
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
  - name: mybox
    image: busybox
    resources:
      requests:
        cpu: 100m
        memory: 128Mi
      limits:
        cpu: 250m
        memory: 256Mi    
    ports:
      - containerPort: 81
    command:
      - sleep
      - "3600"
```
## Get some info

```
kubectl get pods -o wide
kubectl describe pod two-containers
```

## Connect to the BusyBox container

```
kubectl exec -it two-containers --container mybox -- /bin/sh
```

## Fetch the HTML page served by the Nginx container

This will output the content of the Web page in the terminal.

```
wget -qO- localhost
```

## Quit

```
exit
```

## Cleanup

```
kubectl delete -f two-containers.yaml --force --grace-period=0
```