## Elementary Sorts
**Cost model.** Number of array accesses & exchanges (i.e. swaps).

| Algorithm          | No. of Array Accesses | No. of Exchanges       |
|--------------------|-----------------------|------------------------|
| **Selection Sort** |                       |                        |
| Best case          | $N^2/2$               | $0$ (Or $N$^{\dagger}$) |
| Average case       | $N^2/2$               | $\sim N$               |
| Worst case         | $N^2/2$               | $N$                    |
| **Insertion Sort** |                       |                        |
| Best case          | $N - 1$               | $0$ (Or $N$^{\dagger}$) |
| Average case       | $\sim N^2/4$          | $\sim N^2/4$           |
| Worst case         | $\sim N^2/2$          | $\sim N^2/2$           |

${\dagger}$ Exact number depending on implementation.
## Quick-find vs. Quick-union
**Cost model.** Number of operations counting only number of array accesses but not other operations such as counter variable initializations & assignments in loops.

*Warning*: The table below needs update to use the $\sim$ notation.

| Algorithm | Initialization | Union | Find |
| --- | --- | --- | --- |
| Quick Find (All Cases) | $N$ | $N$ | $1$ |
| Quick Union (Worst Case) | $N$ | $N^{\dagger}$ | $N$ |

${\dagger}$ Including cost of finding roots.

*Analysis.* Quick-find, under all curcumstances, is effective at *Find* method but it doesn't working well with *Union*. Quick-union on other hand **CAN** potentially be effective at *Union* but it **MAY** not be working well with *Find*.
However, in worst case, Quick-union performs worse than Quick-find even on *Union*.
