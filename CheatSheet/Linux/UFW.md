A firewall is a security mechanism that acts as a barrier between a computer network and external networks (such as the Internet) or between different network segments.

The UFW utility is not installed on the Debian 12 system by default.
## Install

```sh
sudo apt install ufw
```

## Enable Firewall
```sh
sudo ufw enable
```

## Disable Firewall
```sh
sudo ufw disable
```

## **Add Firewall Rule**

Add a firewall rule other than the default.
[refer this for info](https://www.digitalocean.com/community/tutorials/how-to-set-up-a-firewall-with-ufw-on-ubuntu-20-04)
```sh
sudo ufw allow <Name>
```

You can also add the rules for the specific protocols, such as tcp, udp:
```sh
sudo ufw allow <Name>/<Protocol>
```

## **Allow Ports (or a Specific Port)**

```sh
sudo ufw allow <port>
```

You can also add the rules for the specific protocols, such as tcp, udp:
```sh
sudo ufw allow <port>/<protocol>
```

## Deny/Reject Incoming/Outgoing Traffic

A deny/reject option denies or rejects any incoming or outgoing traffic on all ports or specific ports. The command in such a case is as follows:
```sh
sudo ufw [deny/reject] [outgoing/incoming]
```
## Status

```sh
sudo ufw status
```

```sh
sudo ufw status verbose
```

## Limit the Connections

If more than attempts are carried out from an IP attempts within 30 seconds, then the limit flag will block that connection to avoid any brute force attack/ hack attempt:
```sh
sudo ufw limit ssh
```

**Reload the Firewall**

Whenever you add/delete any firewall rule or allow/deny any network connection, you have to reload the firewall. The firewall can be reloaded using the command as follows:
```sh
sudo ufw reload
```