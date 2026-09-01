## Workloads

- A workload is an application running on Kubernetes
    - Pod
        - Represents a set of running containers
    - ReplicaSet
    - Deployment
    - StatefulSet
    - DaemonSet _(Note: Spelled "DeamonSet" in the image)_
        - Provide node-local facilities, such as a storage driver or network plugin
    - Tasks that run to completion
        - Job
        - CronJob

## ReplicaSets

- Primary method of managing pod replicas and their lifecycle to provide self-healing capabilities
- Their job is to always ensure the desired number of pods are running
- While you can create ReplicaSets, the recommended way is to create Deployments
![[Pasted image 20260531160245.png]]

**Use DEPLOYMENTS instead of Replica Sets in prod**

### kubectl - ReplicaSets Cheat Sheet

|Command|Description|
|---|---|
|`kubectl apply -f [definition.yaml]`|Create a ReplicaSet|
|`kubectl get rs`|List ReplicaSets|
|`kubectl describe rs [rsName]`|Get info|
|`kubectl delete -f [definition.yaml]`|Delete a ReplicaSet|
|`kubectl delete rs [rsName]`|Same but using the ReplicaSet name|

---
## Create the ReplicaSet

```
kubectl apply -f rs-example.yaml
```

rs-example.yaml
```yml
apiVersion: apps/v1
kind: ReplicaSet
metadata:
 name: rs-example
spec:
 replicas: 3
 selector:
   matchLabels:
     app: nginx
     type: front-end
 template: 
   metadata:
     labels:
       app: nginx
       type: front-end
   spec:
     containers:
     - name: nginx
       image: nginx:alpine
       resources:
         requests:
           cpu: 100m
           memory: 128Mi
         limits:
           cpu: 250m
           memory: 256Mi 
       ports:
       - containerPort: 80
```
## Get the pods list

```
kubectl get pods -o wide
```

## Get the ReplicaSet name

```
kubectl get rs
```

## Describe the ReplicaSet

```
kubectl describe rs rs-example
```

## Cleanup

```
kubectl delete -f rs-example.yaml
```

---
# Deployment

- A deployment manage a single pod template.
- You create a deployment for each microservice
    - front-end
    - back-end
    - image-processor
    - creditcard-processor

![[Pasted image 20260531162212.png]]

![[Pasted image 20260531162242.png]]

![[Pasted image 20260531162307.png]]

### kubectl - Deployments Cheat Sheet

|Command|Description|
|---|---|
|`kubectl create deploy [deploymentName] --image=busybox --replicas=3 --port=80`|The imperative way _(Note: "Kubectl" is capitalized in the image)_|
|`kubectl apply -f [definition.yaml]`|Create a deployment|
|`kubectl get deploy`|List deployments|
|`kubectl describe deploy [deploymentName]`|Get info|
|`kubectl get rs`|List replicasets|
|`kubectl delete -f [definition.yaml]`|Delete a deployment|
|`kubectl delete deploy [deploymentName]`|Same but using the deployment name|

---
## Create the Deployment

```
kubectl apply -f deploy-example.yaml
```

deploy-example.yaml
```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: deploy-example
spec:
  replicas: 3
  revisionHistoryLimit: 3
  selector:
    matchLabels:
      app: nginx
      env: prod
  template:
    metadata:
      labels:
        app: nginx
        env: prod
    spec:
      containers:
      - name: nginx
        image: nginx:alpine
        resources:
          requests:
            cpu: 100m
            memory: 128Mi
          limits:
            cpu: 250m
            memory: 256Mi        
        ports:
        - containerPort: 80
```
## Get the pods list

```
kubectl get pods -o wide
```

## Describe the pod

```
kubectl describe pod deploy-example
```

## Get the Deployment info

```
kubectl get deploy
kubectl describe deploy deploy-example
```

## Get the ReplicaSet name

```
kubectl get rs
```

## Describe the ReplicaSet

```
kubectl describe rs
```

## Cleanup

```
kubectl delete -f deploy-example.yaml
```

---

# DaemonSet

- Ensures all Nodes (or a subset) run an instance of a Pod
- Scheduled by the scheduler controller and run by the daemon controller
- As nodes are added to the cluster, Pods are added to them
- Typical uses
    - Running a cluster storage daemon
    - Running a logs collection daemon on every node
    - Running a node monitoring daemon on every node

![[Pasted image 20260531163559.png]]
![[Pasted image 20260531163619.png]]

## kubectl - DaemonSets Cheat Sheet

|Command|Description|
|---|---|
|`kubectl apply -f [definition.yaml]`|Create a DaemonSet|
|`kubectl get ds`|List DaemonSets|
|`kubectl describe ds [rsName]`|Get info _(Note: Uses the incorrect placeholder `[rsName]` instead of `[dsName]`)_|
|`kubectl delete -f [definition.yaml]`|Delete a DaemonSet|
|`kubectl delete ds [rsName]`|Same but using the DaemonSet name _(Note: Also incorrectly uses `[rsName]`)_|

---

Let's deploy a Busybox as a DaemonSet.

## Create the Deployment

```
kubectl apply -f daemonset.yaml
```

daemonset.yaml
```yml
apiVersion: apps/v1
kind: DaemonSet
metadata:
  name: daemonset-example
  labels:
    app: daemonset-example
spec:
  selector:
    matchLabels:
      app: daemonset-example
  template:
    metadata:
      labels:
        app: daemonset-example
    spec:
      tolerations:
      - key: node-role.kubernetes.io/master
        effect: NoSchedule
      containers:
      - name: busybox
        image: busybox
        args:
        - sleep
        - "10000"
```
## Get the pods list

There should be one for each worker node.

```
kubectl get pods --selector=app=daemonset-example -o wide
```

## Cleanup

```
kubectl delete -f daemonset.yaml
```

---

# Statefulset

![[Pasted image 20260531164437.png]]

**StatefulSet**

- For Pods that must persist or maintain state
- Unlike a Deployment, a StatefulSet maintains a sticky identity for each of their Pods
- Each has a persistent identifier (name-x)
- If a pod dies, it is replaced with another one using the identifier
- Creates a series of pods in sequence from 0 to X and deletes them from X to 0
- Typical uses
    - Stable, unique network identifiers
    - Stable, databases using persistent storage

![[Pasted image 20260531164642.png]]
![[Pasted image 20260531164704.png]]
![[Pasted image 20260531164734.png]]

**kubectl - StatefulSets Cheat Sheet**

|Command|Description|
|---|---|
|`kubectl apply -f [definition.yaml]`|Create a StatefulSet|
|`kubectl get sts`|List StatefulSets|
|`kubectl describe sts [rsName]`|Get info _(Note: Uses the incorrect placeholder `[rsName]` instead of `[stsName]`)_|
|`kubectl delete -f [definition.yaml]`|Delete a StatefulSet|
|`kubectl delete sts [rsName]`|Same but using the StatefulSet name _(Note: Also incorrectly uses `[rsName]`)_|

---
## Create the Deployment ( Req. cloud file)

```
kubectl apply -f statefulset.yaml
```

statefulset.yaml
```yml
apiVersion: v1
kind: Service
metadata:
  name: nginx-headless
  labels:
    run: nginx-sts-demo
spec:
  ports:
  - port: 80
    name: web
  clusterIP: None   # this is the trick to make this headless
  selector:
    run: nginx-sts-demo
---
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: nginx-sts
spec:
  serviceName: nginx-headless
  replicas: 3
  selector:
    matchLabels:
      run: nginx-sts-demo
  template:
    metadata:
      labels:
        run: nginx-sts-demo
    spec:
      containers:
      - name: nginx
        image: nginx
        volumeMounts:
        - name: www
          mountPath: /var/www/
  volumeClaimTemplates:
  - metadata:
      name: www
    spec:
      storageClassName: hostpath
      accessModes:
        - ReadWriteOnce
      resources:
        requests:
          storage: 10Mi
```

## Get the pods list

```
kubectl get pods -o wide
```

## Get a list of the PersistentVolumes Claims

```
kubectl get pvc
```

## Create a file in nginx-sts-2

Open a session in nginx-sts-2 and create a file in the folder mapped to the volume.

```
kubectl exec nginx-sts-2 -it -- /bin/sh
cd var/www
echo Hello > hello.txt
```

## Modify the default Web page

```
cd /usr/share/nginx/html
cat > index.html
Hello
Ctrl-D
exit
```

## Open a session in nginx-sts-0 and reach nginx-sts-2

```
kubectl exec nginx-sts-0 -it -- /bin/sh
curl http://nginx-sts-2.nginx-headless
exit
```

## Delete pod 2

Delete a pod and watch as it is recreated with the same name.

```
kubectl delete pod nginx-sts-2
```

## Is the file still there?

Open a session in nginx-sts-2 and see if the file is still present.

```
kubectl exec nginx-sts-2 -it -- /bin/sh
ls var/www
exit
```

## Cleanup

```
kubectl delete -f statefulset.yaml
kubectl delete pvc www-nginx-sts-0
kubectl delete pvc www-nginx-sts-1
kubectl delete pvc www-nginx-sts-2
```

---

# Job

- Workload for short lived tasks
- Creates one or more Pods and ensures that a specified number of them successfully terminate
- As pods successfully complete, the Job tracks the successful completions
- When a specified number of successful completions is reached, the Job completes
- By default, jobs with more then 1 pod will create them one after the other. To create them at the same time, add parallelism.

![[Pasted image 20260531172026.png]]

### kubectl - Jobs Cheat Sheet

|Command|Description|
|---|---|
|`kubectl create job [jobName] --image=busybox`|The imperative way|
|`kubectl apply -f [definition.yaml]`|Create a Job|
|`kubectl get job`|List jobs|
|`kubectl describe job [jobName]`|Get info|
|`kubectl delete -f [definition.yaml]`|Delete a job|
|`kubectl delete job [jobName]`|Same but using the Job name|

---
## Create the Job

```
kubectl apply -f job.yaml
```

job.yaml
```
apiVersion: batch/v1
kind: Job
metadata:
  name: hello
spec:
  template:
    spec:
      containers:
      - name: busybox
        image: busybox
        command: ["echo", "Hello from the Job"]
      restartPolicy: Never
```
## Get the jobs list

```
kubectl get jobs
```

## Get more info

```
kubectl describe job
```

## Get the pod name

Get the pod's log. Something starting with **hello-**

```
kubectl get pods
```

## Get the jobs list

Get the container's log. You should see **Hello from the Job**.

```
kubectl logs <podName>
```

## Cleanup

```
kubectl delete -f job.yaml
```

---

# CronJob

- An extension of the Job
- Provides a method of executing jobs on a cron-like schedule
- UTC only

```
# ┌───────────── minute (0 - 59)
# │ ┌───────────── hour (0 - 23)
# │ │ ┌───────────── day of the month (1 - 31)
# │ │ │ ┌───────────── month (1 - 12)
# │ │ │ │ ┌───────────── day of the week (0 - 6) (Sunday to Saturday;
# │ │ │ │ │               7 is also Sunday on some systems)
# │ │ │ │ │
# * * * * * command to execute
```

https://en.wikipedia.org/wiki/Cron

![[Pasted image 20260531173349.png]]
![[Pasted image 20260531173405.png]]


### kubectl - CronJobs Cheat Sheet

|Command|Description|
|---|---|
|`kubectl create cronjob [jobName] --image=busybox --schedule="*/1 * * * *" -- bin/sh -c "date;"`|The imperative way|
|`kubectl apply -f [definition.yaml]`|Create a CronJob|
|`kubectl get cj`|List CronJobs|
|`kubectl describe cj [jobName]`|Get info|
|`kubectl delete -f [definition.yaml]`|Delete a CronJob|
|`kubectl delete cj [jobName]`|Same but using the CronJob name|

---
## Create the Job

```
kubectl apply -f cronjob.yaml
```

cronjob.yaml
```yml
apiVersion: batch/v1
kind: CronJob
metadata:
  name: hello-cron
spec:
  schedule: "* * * * *"
  jobTemplate:
    spec:
      template:
        spec:
          containers:
          - name: busybox
            image: busybox
            command: ["echo", "Hello from the CronJob"]
          restartPolicy: Never
```
## Get the jobs list

```
kubectl get cronjobs
```

## Get more info

```
kubectl describe cronjob
```

## Get the pod name

Get the pod's log. Something starting with **hello-**

```
kubectl get pods
```

## Get the jobs list

Get the container's log. You should see **Hello from the Job**.

```
	kubectl logs <podName>
```

## Cleanup

```
kubectl delete -f cronjob.yaml
```