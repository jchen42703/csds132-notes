# Weighted Job Scheduling in O(n Log n) time
* Resources:
  * https://www.geeksforgeeks.org/weighted-job-scheduling-log-n-time/?ref=lbp
  * https://leetcode.com/problems/maximum-profit-in-job-scheduling/discuss/409009/JavaC%2B%2BPython-DP-Solution
* For scheduling jobs with `{startTime, endTime, profit}`

## Algorithm
1. Sort the jobs by non-decreasing `endTime`
2. For each `i` from `1 to n`:
  * determine the max of `jobs[0..i]`
    * compare the inclusion of `job[i]` to the schedule to the exclusion of `job[i]` to the schedule -> Take the max.
    * To find the profit with inclusion of `job[i]`. we need to find the latest job that doesn’t conflict with `job[i]`. The idea is to use Binary Search to find the latest non-conflicting job.
