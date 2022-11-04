###### 1. [그리디]
#현재 상황에서 특정한 기준에 따라 가장 좋아 보이는 것만을 선택
#다익스트라 최단 경로(특정노드까지 최단 거리를 계산한 다음 메모리저장), 크루스칼 알고리즘(최소 신장 트리,사이클이 존재x) 등 다양한 알고리즘이 그리드에 속함
#대표적인 문제 : 거스름돈(거슬러야할 동전의 최소 개수), 1이될때까지(1빼기 혹은 K나누기, 나누기가 빠르게 값을 줄임)

###### 2. [구현]
#모든유형
#2000만 이상인 list 주의
#대표적인 문제 : 상하좌우 문제, 시각문제, 완전탐색(모든 경우의 수), 시물레이션(논리를 그대로 코드로 작성)
# 완전탐색 : 모든 경우를 탐색
# - 반복문, 재귀함수를 적절히 사용하고 예외 케이스를 모두 확인
# - DFS/BFS 사용

#순열: 순서를 정해서 나열 , (a,b) (b,a)는 다름
#조합: 순서와 상관없이 n개를 고름, (a,b) (b,a)는 같음
from itertools import permutations, product, combinations, combinations_with_replacement
a = ['a', 'b', 'c']
#순서를 고려한 순열: list(permutations(a,2))  -> [('a', 'b'), ('a', 'c'), ('b', 'a'), ('b', 'c'), ('c', 'a'), ('c', 'b')]
#중복을 허용한 순열: list(product(a, repeat=2)) -> [('a', 'a'), ('a', 'b'), ('a', 'c'), ('b', 'a'), ('b', 'b'), ('b', 'c'), ('c', 'a'), ('c', 'b'), ('c', 'c')]
#만들수 있는 조합: list(combinations(a,2)) -> [('a', 'b'), ('a', 'c'), ('b', 'c')]
#중복을 허용한 조합: list(combinations_with_replacement(a,2)) -> [('a', 'a'), ('a', 'b'), ('a', 'c'), ('b', 'b'), ('b', 'c'), ('c', 'c')]

#2차원 초기화
m , n = 2, 3
data = [[0] * m for _ in range(n)] # -> [[0, 0], [0, 0], [0, 0]]

#상하좌우
dx = [0,0,-1,1]
dy = [1,-1,0,0]

##### 3. [DFS/BFS]
# 자료구조 : 데이터를 표현하고 관리하고 처리하기 위한 구조 -> 스택, 큐
# 삽입(push), 삭제(pop)
# 스택 : 선입후출, 박스 쌓기 
stack = []
stack.append(1)
stack.append(2)
stack.append(3)
stack.pop() # -> 3, stack -> [1,2]
#큐 : 선입선출, 줄서기  
from collections import deque #되도록 이걸 사용, 큐/스택 모두 채택, 리스트보다 빠름, 대부분 테스트에서 사용
queue = deque()
queue.append(1)
queue.append(2)
queue.append(3)
queue.append(4)
queue.popleft() # -> 1
queue.pop() # -> 4
list(queue) #[2, 3]
# 탐색 : 데이터중 원하는 데이터를 찾는 과정 -> 대표적 DFS/BFS
# 인접행렬: 각 열값에 간선이 있으면 1(or 가중치)표시
# 인접리스트 : 각 열에 연결되어 있는 노드를 저장
# DFS : 깊이 우선 탐색, 그래프의 깊은 부분을 우선적으로 탐색, 가장 깊숙이 위치하는 노드에 닿을 때까지 확인(탐색), 한 줄씩 탐색
# - 동작원리는 스택이지만 재귀함수 사용
#예상문제 : 음료수얼음(0,1확인하고 방문), 
graph = [ #인접리스트 노드1은 2,3,8과연결, 노드2는 1,7과 연결 ... 
    [],
    [2,3,8],
    [1,7],
    [1,4,5],
    [3,5],
    [3,4],
    [7],
    [2,6,8],
    [1,7]
]
#각 노드가 방문된 정보를 리스트 자료형으로 표현(1차원 리스트)
visited = [False] * 9

def dfs(graph, v, visited):
    #현재 노드 방문 처리
    visited[v] = True
    print(v, end=' ')
    #현재 노드와 연결된 다른 노드를 재귀적으로 방문
    for i in graph[v]:
        if not visited[i]:
            dfs(graph, i, visited)

dfs(graph,1,visited)

# BFS : 너비 우선 탐색, 가까운 노드부터 탐색하는 알고리즘, 큐를 사용하여 인접 노드를 먼저 넣으면 먼저 확인(선입선출)
# - DFS보다 빠르다 
# 예상문제: 미로탈출(시작점에서 가까운 노드부터 차례로 탐색), 특정거리의 도시찾기(모든 간선비용이 동일할때 최단거리를 찾음)
from collections import deque
def bfs(graph, start, visited):
    queue = deque([start])
    #현재 노드 방문 처리
    visited[start], True
    #큐가 빌 때까지 반복
    while queue:
        v = queue.popleft()
        print(v, end=' ')
        #해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True
bfs(graph, 1, visited)


##### 4. [정렬]
#데이터를 특정한 기준에 따라 순서대로 나열 
#이진탐색의 전처리 과정
#선택정렬 : 가장 작은 데이터를 선택해 맨 앞 데이터와 바꾸고 다음 작은 데이터를 선택해 두번째 데이터와 바꾸는걸 반복
# - 느림, 가장 작은 데이터를 찾음
#삽입정렬 : 데이터가 적절한 위치로 삽입
# - 거의 정렬이 되어 있으면 빠름, 앞부터 서로 자리를 바꿔가며 정렬
#퀵정렬 : 기준(피벗)에 따라 분할하며 정렬
# - 정렬이 되어 있지 않아야 빠름
 # -  호어분할방식: 
    # 1. 리스트에서 첫번째 데이터를 피벗으로 정한다.
    # 2. 리스트의 왼쪽에서 피벗보다 큰데이터를 오른쪽에서 피벗보다 작은데이터를 찾아 서로 교환
    # 3. 가운데까지 반복하고 가운데에 피벗데이터가 들어감
    # => 호어분할다음 피벗 양쪽을 똑같이 호어분할 진행하여 정렬 
#계수정렬: 특정 조건이 무합할 때만 사용, 매우빠름, 인덱스 활용, 메모리
#정렬 라이브러리
# sort() -> 하나의 조건만 있을때
# sroted() -> 여러조건시 key 사용

##### 5. [이진탐색]
# 내부의 데이터가 정렬, 절반씩 좁혀가며 탐색, 중간지점과 비교
#  (array, target, start, end)
#bisect 클래스 : 이진탐색 라이브러리 , 빠르기 때문에 이진탐색이 아니더라도 사용하면 좋음
#자주 출제되어 암기하기
#예상문제: 떡볶이 떡 만들기(while로 부족할경우 왼쪽, 많을경우 오른쪽 탐색,높이를 0~최대값 의 중간값으로 해야 시간 절약)
#재귀사용하거나 while 사용
def binary_search_01(array, target, start, end):
    if start > end:
        return None
    mid = (start + end) //2
    if array[mid] == target:
        return mid
    elif array[mid] > target: #중간값보다 작은 경우 오른쪽 확인
        return binary_search_01(array, target, start, mid-1)
    else: #중간값보다 큰 경우 왼쪽 확인
        return binary_search_01(array,target, mid+1, end)
result = binary_search_01(array, target, 0, n-1)
# 이진 탐색 트리
#부모 노드보다 왼쪽 자식 노드가 작다
#부모 노드보다 오른쪽 자식 노드가 크다 
# 왼쪽 자식노드 < 부모노드 < 오른쪽 자식노드
# 이진탐색처럼 찾는값이 부모보다 작으면 왼쪽만 탐색, 크면 오른쪽만 탐색
# bisect 해당값이 왼쪽으로 몇번째, 오른쪽으로 몇번째
from bisect import bisect_left, bisect_right
def count_by_range(a, left_value, right_vlaue):
    right_index = bisect_right(a, right_vlaue)
    left_index = bisect_left(a,left_value)
    return right_index - left_index #해당값 개수는
    
    
##### 6.[다이나믹 프로그래밍]
#해결된 부분을 메모리에 기록하여 한번 계산한 답은 다시는 계산하지 않도록, 점화식(인접한 항들의 관계식)
# 피보나치 수열, 그래프 이론, 플로이드 워셜 알고리즘 등 활용
#탑다운 : 하향식
#보텀업 : 상향식, 다이나믹의 전형적 방식, 결과저장 메모리사용
#문제를 완전탐색 알고리즘으로 접근했을때 시간이 오래걸리거나 반복적이면 다이나믹으로 

##### 7. [최단경로]
#1.한 지점에서 다른 톡정 지점까지의 최단 경로를 구해야 하는 경우
#2.모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구해야하는 경우
#다익스트라 최단 경로 알고리즘 : 특정한 노드에서 출발하여 다른 노드로 가는 각각의 최단 경로를 구해줌
# - 한 지점에서 다른 특정 지점까지의 최단경로를 구할때
# - 가장 적은 비용을 하나씩 선택하는 로직
# - 단계마다 방문하지 않은 노드 중에서 가장 최단 거리가 짧은 노드르 선택한 뒤에 그 노드를 거쳐 가는 경우를 확인하며 최단 거리 갱신
# - 각 노드에 대한 현재까지의 최단 거리 정보를 리스트에 저장(최단거리테이블)
# - 힙 자료구조: 우선순위 큐를 구현하기 위한 자료구조중 하나
# - 우선순위 큐: 우선순위가 가장 높은 데이터를 가장 먼저 삭제 heapq
INF = int(1e9) #무한을 의미하는 값으로 10억 설정
import heapq
graph = [[] for i in range(n+1)] #리스트안 리스트 만들기  [[], [], [], []]
distance = [INF] * (n+1) #무한으로 초기화 [1000000000, 1000000000, 1000000000, 1000000000]
#모든 간선정보
for _ in range(m):
    a,b,c = map(int, input().split())
    #로드 a는 로드 b까지 c거리
    graph[a].append((b,c))

def dijkstra(start):
    q = []
    #시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
    heapq.heappush(q, (0, start))
    distance[start] = 0
    while q: #큐가 비어있지 않다면
        #가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
        dist, now = heapq.heappop(q)
        #현재 노드가 이미 처리된 적이 있는 노드라면 무시
        if distance[now] < dist:
            continue
        #현재 노드와 연결된 인접한 노드들을 확인
        for i in graph[now]:
            cost = dist + i[1]
            #현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))
    
#다익스트라 알고리즘 수행
dijkstra(start)
    
for i in range(1,n+1):
    #도달할 수 없는 경우, 무한(INFINITY)이라고 출력
    if distance[i] == INF:
        print("INFINIFY")
    #도달할 수 있는 경우 거리를 출력
    else:
        print(distance[i])

#플로이드 워셜 알고리즘 : 모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구해야 하는 경우
# - 거쳐가는 정점을 하나씩 다 설정을 하여 직접 확인
# - 거쳐가는 정점을 기준으로 최단 거리를 구하도록 구성
# - 단계마다 거쳐 가는 노드를 기준으로 갱신
# - 시간이 많이 걸림 (n이 500까지 정도만 사용하기-> 다익스트라로 변경), 간선 수가 많으면 다익스트라 알고리즘보다 빠를 수가 있음.
# 3중 반복문 사용
# for k in range(1, n+1):
#     for a in range(1, n+1):
#         for b in range(1, n+1):
#             adj[a][b] = min(adj[a][b], adj[a][k]+ adj[k][b])
# - 겉보기엔 경로문제로 보이지 않더라도 최소비용등 다양하게 쓰임
INF = int(1e9)
#2차원 리스트(그래프 표현)를 만들고 모든 값을 무한으로 초기화
graph = [[INF] * (n+1) for _ in range(n+1)] #[[1000000000, 1000000000, 1000000000], [1000000000, 1000000000, 1000000000], [1000000000, 1000000000, 1000000000]]
#자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
for a in range(1, n+1):
    for b in range(1, n+1):
        if a == b:
            graph[a][b] = 0

#각 간선에 대한 정보를 입력받아, 그 값으로 초기화
for _ in range(m):
    #A에서 B로 가는 비용은 C라고 설정
    a, b, c = map(int, input().split())
    graph[a][b] = c            

#점화식에 따라 플로이드 워셜 알고리즘 수행
for k in range(1, n+1):
    for a in range(1, n+1):
        for b in range(1, n+1):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])
            
#수행된 결과를 출력
for a in range(1, n+1):
    for b in range(1, n+1):
        #도달할 수 없는 경우,
        if graph[a][b] == INF:
            print("INFINIFY")
        else:
            print(graph[a][b], end='  ')
    print()

##### 8. [그래프]
#그래프란 노드와 노드 사이에 연결된 간선의 정보를 가지는 자료구조
#서로 다른 개체가 연결되어 있으면 그래프 알고리즘
#서로소 집합 : 공통 원소가 없는 두 집합, union-find(합치기-찾기)연산으로 구성
# - 모든 노드는 자신이 속한 집합을 찾을 때 루트 노드를 재귀적으로 찾음
# - 최소 신장 트리를 찾는 크루스칼 알고리즘에서 사용됨

#신장 트리 : 하나의 그래프가 있을 때 모든 노드를 포함하는 부분 그래프
# 모든 섬을 도로로 연결 등의 문제에서

#크루스칼 알고리즘 : 최소 비용의 신장 트리를 찾아줌 , 간선비용이 적은 순서대로 트리를 만들어감

#위상 정렬 알고리즘 : 방향 그래프의 모든 노드들을 방향성에 거스르지 않도록 순서대로 나열
# 선수과목을 고려한 학습 순서등의 문제에서
# 큐 자료구조를 이용

#서로소와 신장트리는 종종 출제, 위상정렬은 어렵기 때문에 마지막에 간혹 출제
parent = [0] * (v+1) #부모 테이블 초기화
#부모 테이블 상에서, 부모를 자기 자신으로 초기화
for i in range(1, v+1):
    parent[i] = i
#특정 원소가 속한 집합을 찾기
def find_parent(parent,x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

#두 원소가 속한 집합을 합치기
def union_parnet(parnet, a, b):
    a = find_parent(parnet,a)
    b = find_parent(parnet,b)
    if a < b:
        parnet[b] = a
    else:
        parnet[a] = b
        
cycle = False #사이클 발생 여부
for i in range(e):
    a, b = map(int, input().split())
    #사이클이 발생한 경우 종료 
    if find_parent(parent, a) == find_parent(parent, b):
        cycle = True
        break
    #사이클이 발생하지 않았다면 합집합(union) 수행
    else:
        union_parnet(parent,a,b)      
        
# 위상 정렬 함수
def topology_sort():
    result = [] #알고리즘 수행 결과를 담으 리스트
    q = deque() #큐 기능을 위한 deque 라이브러리 사용
    
    #처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
    for i in range(1, v+1):
        if indegree[i] == 0:
            q.append(i)
    
    #큐가 빌 때까지 반복
    while q:
        #큐에서 원소 꺼내기
        now = q.popleft()
        result.append(now)
        #해당 원소와 연결된 노드들의 진입차수에서 1빼기
        for i in graph[now]:
            indegree[i] -= 1
        #새롭게 집입차수가 0이되는 노드를 큐에 삽입
            if indegree[i] == 0:
                q.append(i)
    
    for i in result:
        print(i, end= " ")