ifconfig eth0 192.168.2.253 netmask 255.255.255.0 
route add default gw 192.168.2.1 
echo "nameserver 8.8.8.8\nnameserver 8.8.8.8" > /etc/resolv.conf
/etc/init.d/networking restart
