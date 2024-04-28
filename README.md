## Quick-find vs. Quick-union
*Cost model.* Number of array accesses.

*Empirical result.*
**Warning**: Numbers in the table below is not updated and may be inaccurate.

| Algorithm                | Initialization  | Union         | Find |
|--------------------------|-----------------|---------------|------|
| Quick Find (All Cases)   | $N$             | $N$           | $1$  |
| Quick Union (Worst Case) | $N$             | $N^{\dagger}$ | $N$  |

${\dagger}$ Including cost of finding roots.

*Analysis.* Quick-find, under all circumstances, is effective at *Find* method, but it doesn't perform well with *Union*. Quick-union on other hand **CAN** potentially be effective at *Union*, but it **MAY** not be working well with *Find*.
However, in worst case, Quick-union performs worse than Quick-find even on *Union*.

## Elementary Sorts
*Cost model.* Number of array accesses & exchanges (i.e. swaps).

*Empirical result.*

| Algorithm          | No. of Array Accesses | No. of Exchanges       |
|--------------------|-----------------------|------------------------|
| **Selection Sort** |                       |                        |
| Best case          | $N^2/2$               | $0$ (Or $N^{\dagger}$) |
| Average case       | $N^2/2$               | $\sim N$               |
| Worst case         | $N^2/2$               | $N$                    |
| **Insertion Sort** |                       |                        |
| Best case          | $N - 1$               | $0$ (Or $N^{\dagger}$) |
| Average case       | $\sim N^2/4$          | $\sim N^2/4$           |
| Worst case         | $\sim N^2/2$          | $\sim N^2/2$           |

${\dagger}$ Exact number depending on implementation.

## Merge Sort
*Cost model.* Number of array accesses & compares.

*Empirical result.*

| Input Model  | No. of Array Accesses        | No. of Compares     |
|--------------|------------------------------|---------------------|
| Best case    | $\approx 5N\lg\ N^{\dagger}$ | $\approx N\lg\ N/2$ |
| Average case | $\approx 6N\lg\ N$           | $\approx N\lg\ N$   |
| Worst case   | $\approx 5N\lg\ N$           | $\approx N\lg\ N/2$ |

${\dagger}$ $\lg$ denotes logarithm base 2.

*Analysis*. Merge sort uses at most $N\lg\ N$ compares and $6N\lg\ N$ array accesses.
