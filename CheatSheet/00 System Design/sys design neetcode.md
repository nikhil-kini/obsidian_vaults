## Computer Architecture

- Disk - HDD, SSD
- RAM 
- CPU
- CPU Cache

## Application Architecture
- Dev
- Build and deploy code
- Server - (logging, metrics, Alerts) to monitor app
- Storage
### Vertical Scaling
- Increase Hardware
### Horizontal Scaling
- Load balancing

## Design Requirements
- Move Data
- Store Data - DB, Blob store etc.
- Transform Data - Computation of stored data

### What is a good design? - Think in-terms of trade-offs
- Availability - uptime/(uptime + downtime) %
- SLO - Service Level Objective, 99.999% availability (5 9's availability)
- SLA - Service Level Agreement, Agreement to maintain certain availability of server

- Reliability - Probability that over system won't fail.
- Fault Tolerance - Ability of system to handle request if there is a faulty system
- Redundancy - Excess server to achieve reliability

- Throughput - How many Request/sec can be processed

- Latency - Amount of time each individual request to complete


## Networking Basics
- IP Address - 32-bit ipv4, 128-bit ipv6
- IP Header - Metadata on Ip Packet
- 7 Layer of Internet
- Public VS Private IP
- Static VS Dynamic IP
- Ports

## TCP vs UDP

- TCP
- Helps to keep the order of the data in client after reviving data packets
- Reliable - Ensures all data are transferred correctly
- 2 way connection establish
- 3 way handshake
- HTTP, SMTP, Websocket
- Slower connection

- UDP
- Faster connection
- 1 way connection
- NO Reliability

## Application Protocols
- RPC - Remote Procedure Call, HTTP, Websocket
- HTTP - Request-Response protocol
- Methods - GET, POST, PUT, DELETE
- Status Code - 200, 404
- SSL/TSL

- Web-sockets
- Stay connected all the time to the server

## API
- REST - Stateless, 
- GraphQL - Query Data
- gRPC - Between API's
 