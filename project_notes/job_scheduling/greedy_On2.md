# [Job Sequencing Problem with Deadlines](https://www.techiedelight.com/job-sequencing-problem-deadlines/)
* __Greedy approach:__ Search for local optimum at each level to hopefully find the global optimum.
* Here, we only have the `deadline` and `profit` for each job.
  * We assume that the duration of each job is only 1 hour.
* The algorithm goes like this:
  1. Sort by the profit.
  2. Then assign each time slot with job with the highest profit.
    * Then rinse and repeat.
* This algorithm is `O(n^2)`.

## Other resources with this algorithm
* https://www.geeksforgeeks.org/job-sequencing-problem/
1. Sort all jobs in decreasing order of profit.
2. Iterate on jobs in decreasing order of profit. For each job, do the following:
  * Find a time slot `i`, such that slot is empty and `i < deadline` and `i` is greatest. Put the job in this slot and mark this slot filled.
  * If no such `i` exists, then ignore the job.
