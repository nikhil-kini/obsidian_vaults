## Method 1
Just go to the "run" dialog (`Alt+F2`), type in `xkill` and your mouse pointer will change to an "x". Point on the application that you want to kill and click, and it'll be killed.

## Method 2

PID of a process by commands `ps aux`, `top`, `htop`
```sh
ps aux
```
or
```sh
top
```
or
```sh
htop
```
or
```sh
ps aux | grep "$appName" | grep -v 'grep'
```

To Kill
```sh
kill "$PID"
```

- `kill -9 <pid_number>` example: `kill -9 8888` effect: same with above but more extreme and forceful.
- `killall <application_name>` example `killall firefox` effect: kill application or application instance that has name firefox. You don't have to know PID number, and this is my most favorite.
- `kill -9 -1` effect: kill all processes including X Server so you can go back to display manager (LightDM, GDM, or KDM). Another name for this command is **relogin**. And this command is a substution for clicking Log Out button.
- `Alt+Printscreen+REISUB` effect: force restart; same with Ctrl+Alt+Del in Windows. It is very useful to avoid HDD damage.
- `Alt+Printscreen+REISUO` effect: force shutdown, like Ctrl+Alt+Del but do shutdown instead of restart. Only differ 1 last char (O and B).

#### Destructive
This is like yanking the power cord out. You risk destroying recently saved data (files not quite actually written to disk) and you will definitely lose anything unsaved. On the upside, it is quick and to the point.

**reBoot**: `alt + SysRq + b`

**shut Off**: `alt + SysRq + o`

Refer this for more [info](https://www.kernel.org/doc/html/latest/admin-guide/sysrq.html)

**To Enable the SysReq**
```sh
cat /proc/sys/kernel/sysrq
```

```sh
echo "1" > /proc/sys/kernel/sysrq
```
*for 1 time use, will not survive the reboot*

```sh
echo "kernel.sysrq = 1" >> /etc/sysctl.d/99-sysctl.conf
```
*for all time use*