The strategy that the default change detector uses to detect changes. When set, takes effect the next time change detection is triggered.

**Remember: Change detection only work if there is change the the memory**

|Member|Description|
|---|---|
|`OnPush: 0`|Use the `CheckOnce` strategy, meaning that automatic change detection is deactivated until reactivated by setting the strategy to `Default` (`CheckAlways`). Change detection can still be explicitly invoked. This strategy applies to all child directives and cannot be overridden.|
|`Default: 1`|Use the default `CheckAlways` strategy, in which change detection is automatic until explicitly deactivated.|
[Check this article for the usage](https://www.digitalocean.com/community/tutorials/angular-change-detection-strategy)
