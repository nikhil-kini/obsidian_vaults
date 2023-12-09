
```sh
ip a
```
For this network interface, you are presented with multiple information :

- **Network adapter general information** : its state (up or down), its MTU as well as the qlen for the Etherner buffer queue;
- **Layer 2 information** : in this case, you are running on the Ethernet protocol with a given MAC address and a broadcast address;
- **Layer 3 information** : what you are probably interested in which is your IPv4 address in [CIDR notation](https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing), the subnet broadcast address as well as the address lifetime (valid_lft and preferred_lft)
- **IPv6 addresses** : this section might not appear in your network adapter configuration as not all interfaces are running IPv6 addresses. If this is the case, it will appear here.

short-version
```sh
hostname -I
```
