## Quick-find vs. Quick-union
*Cost model.* Number of operations counting only number of array accesses but not other operations such as counter variable initializations & assignments in loops.
| Algorithm | Initialization | Union | Find |
| --- | --- | --- | --- |
| Quick Find (All Cases) | $N$ | $N$ | $1$ |
| Quick Union (Worst Case) | $N$ | $N^{\dagger}$ | $N$ |

${\dagger}$ Including cost of finding roots.

*Analysis.* Quick-find, under all curcumstances, is effective at *Find* method but it doesn't working well with *Union*. Quick-union on other hand **CAN** potentially be effective at *Union* but it **MAY** not be working well with *Find*.
However, in worst case, Quick-union performs worse than Quick-find even on *Union*.
