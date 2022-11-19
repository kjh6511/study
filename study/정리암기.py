### 01
### DFS 깊이우선탐색
## 가장 깊숙히 닿을때 까지
def dfs (graph, v, visited):
    visited[v] = True #방문처리
    print (v, end='')
    for i in graph[v]:
        if not visited[i]:
            dfs(graph, i, visited)


###02
### BFS 너비 우선 탐색
## 서로인접한 노드 부터
from collections import deque
def bfs(graph, start, visited):
    queue = deque([start])
    visited[start] = True
    while queue:
        v = queue.popleft()
        print(v, end='')
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True


### 03
### 이진탐색
## 가운데 기준 값으로 왼,오른쪽 비교, 순서대로 되어 있어야함
def binary_search(array, target, start, end):
    if start > end:
        return None
    mid = (start+end) // 2
    if array[mid] == target:
        return mid
    elif array[mid] > target:
        return binary_search(array, target, start, mid-1)
    else:
        return binary_search(array, target, mid+1, end)
result = binary_search(array, target, 0, n-1)


### 04
### 이진탐색트리
from bisect import bisect_left, bisect_right
def count_by_range(a, left_value, right_value):
    right_index = bisect_right(a,right_value)
    left_index = bisect_left(a,left_value)
    return right_index - left_index


### 05
### 최단경로 다익스트라
## 한지점에서 각각의 경로를 구함
import heapq



