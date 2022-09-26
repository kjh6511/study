####최단경로
#1.한 지점에서 다른 톡정 지점까지의 최단 경로를 구해야 하는 경우
#2.모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구해야하는 경우
# 각 지점은 노드, 연결된 도로는 간선

def study_01():
## 다익스트라 최단 경로 알고리즘 : 특정한 노드에서 출발하여 다른 노드로 가는 각각의 최단 경로를 구해줌
#그리디 알고리즘
#가장 비용이 적은 노드르 선택
#1차원 리스트를 계속 갱신


### 간단한 다익스트라
    import sys 
    input = sys.stdin.readline
    INF = int(1e9) #무한을 의미하는 값으로 10억 설정 
    
    #노드의 개수, 간선의 개수 입력받기
    n,m = map(int, input().split())
    #시작 노드번호 입력 받기
    start = int(input())
    #각 노드에 연결되어 있는 노드에 대한 정보를 담는 리스트를 만들기 
    graph = [[] for i in range(n+1)]
    #방문한 적이 있는지 체크하는 목적의 리스트 만들기
    visited = [False] * (n+1)
    #최단 거리 테이블을 모두 무한으로 초기화
    distance = [INF] * (n+1)
    
    #모든 간선 정보를 입력받기
    for _ in range(m):
        a,b,c = map(int, input().split())
        #a번 노드에서 b번 노드로 가는 비용이 c라는 의미
        graph[a].append((b,c))
    
    #방문하지 않은 노드중에서, 가장 최단 거리가 짧은 노드의 번호를 반환 
    def get_smallest_node():
        min_value = INF
        index = 0 #가장 최단 거리가 짧은 노드(인덱스)
        for i in range(1, n +1):
            if distance[i] < min_value and not visited[i]:
                min_value = distance[i]
                index = i
        return index
    
    def dijkstra(start):
        #시작 노드에 대해서 초기화
        distance[start] = 0
        visited[start] = True
        for j in graph[start]:
            distance[j[0]] = j[1]
        #시작 노드를 제외한 전체 n-1개의 노드에 대해 반복
        for i in range(n-1):
            #현재 최단 거리가 가장 짧은 노드를 꺼내서, 방문 처리
            now = get_smallest_node()
            visited[now] = True
            #현재 노드와 연결된 다른 노드를 확인
            for j in graph[now]:
                cost = distance[now] + j[1]
                #현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
                if cost < distance[j[0]]:
                    distance[j[0]] = cost
    
    #다익스트라 알고리즘 수행
    dijkstra(start)
    
    #모든 노드로 가기 위한 최단 거리르 출력
    for i in range(1,n+1):
        #도달할 수 없는 경우, 무한(INFINITY)이라고 출력
        if distance[i] == INF:
            print("INFINIFY")
        #도달할 수 있는 경우 거리를 출력
        else:
            print(distance[i])
                
def study_02():
    ####개선된 다익스트라 
       # 힙 : 우선순위 큐를 구현하기 위한 자료구조중 하나
       # 우선순위 큐: 우선순위가 가장 높은 데이터를 가장 먼저 삭제 
    
    #최단거리를 우선순위큐를 사용
    
    import heapq
    import sys
    input = sys.stdin.readline
    INF = int(1e9)
    
    n,m = map(int, input().split())
    start = int(input())
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
            #현재 노드와 연결된 인접한 오드들을 확인
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
            

def study_03():
    ###플로이드 워셜 알고리즘
    #모든 지점에서 다른 모든 지점까지 최단 경로를 모두 구해야하는 경우
    #다이나믹 프로그래밍
    INF = int(1e9)
    
    #노드 개수 및 간선 개수
    n = int(input())
    m = int(input())
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
        
        
def study_04():
    #### 실전문제 미래도시
    ## 플로이드 워셜 알고리즘 - 모든 최단 거리를 봐야함
    
    INF = int(1e9) #무한을 의미하는 값
    
    #노드갯수 간선 갯수 입력
    n,m = map(int, input().split())
    #2차원 리스트(그래프 표현)를 만드고, 모든값 무한을 초기화
    graph = [[INF] * (n+1) for _ in range(n+1)] 

    #자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
    for a in range(1, n+1):
        graph[a][a] = 0
    
    #각선에 대한 값입력 받아 그 값으로 초기화
    for _ in range(m):
        #A와B가 서로에게 가는 비용은 1이라고 설정
        a,b = map(int,input().split())
        graph[a][b] = 1
        graph[b][a] = 1
    
    #거쳐갈 노드 X와 최종 목적지 노드 K를 입력받기
    x, k = map(int, input().split())
    
    #점화식에 따라 플로이드 워셜 알고리즘 수행
    for k in range(1, n+1):
        for a in range(1, n+1):
            for b in range(1, n+1):
                graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])
                
    #수행된 결과를 출력
    distance = graph[1][k] + graph[k][x]
    
    #도달할 수 없는 경우 -1을 출력
    if distance >= INF:
        print("-1")
    #도달할 수 있다면, 최단거리를 출력
    else:
        print(distance)

def study_05():
    ##실전문제 전보
    #한도시에서 다른도시까지 이므로 다익스트라 알고리즘    
    
    import heapq
    import sys
    
    input = sys.stdin.readline
    INF = int(1e9) #무한을 의미하는 10억
    
    n, m, start = map(int, input().split())
    #노드,간선 정보 리스트만들기
    graph = [[] for i in range(n+1)]
    #최단거리 리스트 무한으로 초기화
    distance = [INF] * (n+1)
    
    #모든 간선 정보 입력받기
    for _ in range(m):
        x, y, z = map(int, input().split())
        #x번 노드에서 y번 노드로 가는 비용이 z라는 의미
        graph[x].append((y,z))
    
    def difkstra(start):
        q = []
        #시작노드로 가기위한 최단경로는 0으로 설정하여, 큐에 삽입
        heapq.heappush(q, (0,start))
        distance[start] = 0
        while q: #q가 비어있지 않다면
            dist, now = heapq.heappop(q)
            if distance[now] < dist:
                continue
            #현재 노드와 연결된 다른 인접한 노드들을 확인
            for i in graph[now]:
                cost = dist + i[1]
                #현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
                if cost < distance[i[0]]:
                    distance[i[0]] = cost
                    heapq.heappush(q, (cost, i[0]))
        
        
    #다익스트라 알고리즘 수행
    difkstra(start)
    
    #도달할 수 있는 노드의 개수
    count = 0
    #도달할 수 있는 노드 중에서, 가장 멀리 있는 노드와의 최단 거리
    max_distance = 0
    for d in distance:
        #도달할 수 있는 노드인 경우
        if d != INF:
            count += 1
            max_distance = max(max_distance,d)
    
    #시작 노드는 제외해야 하므로 count-1을 출력
    print(count-1, max_distance)
    
    
####### 실행용
study_05()
