###최단경로 문제
# - 다익스트라 : 어려움, 한 지점에서 다른 모든 지점까지의 최단 경로를 계산
# 단계마다 방문하지 않은 노드 중에서 가장 최단 거리가 짧은 노드르 선택한 뒤에 그 노드를 거쳐 가는 경우를 확인하며 최단 거리 갱신
# 우선순위 큐 사용

# - 플로이드 워셜: 쉬운편, 모든 지점에서 다른 모든 지점까지의 경로를 계산
# 다이나믹 프로그래밍 사용
# 단계마다 거쳐 가는 노드를 기준으로 갱신
# 3중 반복문 사용
# for k in range(1, n+1):
#     for a in range(1, n+1):
#         for b in range(1, n+1):
#             adj[a][b] = min(adj[a][b], adj[a][k]+ adj[k][b])
#겉보기엔 경로문제로 보이지 않더라도 최소비용등 다양하게 쓰임

def study_01():
    ## 플로이드
    # 최단경로 문제
    # 간선이 여러 개일 수 있음, 짧은 1만 고려
    # 플로이드 워셜
    
    INF = int(1e9)
    
    n, m = 5, 14 #도시, 버스
    bus_info = [[1,2,2], [1,3,3], [1,4,1], [1,5,10], [2,4,2], [3,4,1], [3,5,1],
                [4,5,3], [3,5,10], [3,1,8], [1,4,2], [5,1,7], [3,4,2], [5,2,4]]
    
    #모든값을 무한대로 초기화
    graph = [ [INF] * (n+1) for _ in range(n+1) ]
    
    #자기 자신에서 자기 자신으로 가는 비용을 0을 초기화
    for a in range(1, n+1):
        for b in range(1, n+1):
            if a == b:
                graph[a][b] = 0
    
    #각 간선에 대한 정보를 입력받아, 그 값으로 초기화
    for a, b, c in bus_info:
        if c < graph[a][b]:
            graph[a][b] = c
    
    # 점화식에 따라 플로이드 워셜 알고리즘을 수행
    for k in range(1, n+1):
        for a in range(1, n+1):
            for b in range(1, n+1):
                graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])
    

    #수행된 결과를 출력
    for a in range(1, n+1):
        for b in range(1, n+1):
            #도달할 수 없는 경우, 0을 출력
            if graph[a][b] == INF:
                print(0, end=" ")
            #도달할 수 있는 경우 거리를 출력
            else:
                print(graph[a][b], end=" ")
    
    print()

def study_02():
    ###정확한 순위
    #최단거리
    #a에서 b로 도달이 가능하거나, b에서 a로 도달이 가능하면 성적비교 가능
    # 500이하 정수이므로 플로이드 워셜 알고리즘 이용
    
    INF = int(1e9) #10억
    
    n, m = 6,6 #학생수, 비교횟수

    data = [[1,5], [3,4], [4,2], [4,6], [5,2], [5,4]]
    
    graph = [ [INF] * (n+1) for _ in range(n+1)]
    
    #자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
    for a in range(1, n+1):
        for b in range(1, n+1):
            if a == b:
                graph[a][b] = 0
    
    #각 간선에 대한 정보를 입력받아, 그 값으로 초기화
    for a, b in data:
        #A에서 B로 가는 비용을 1로 설정
        graph[a][b] = 1
    
    #점화식에 따라 플로이드 워셜 알고리즘 수행
    for k in range(1, n+1):
        for a in range(1, n+1):
            for b in range(1, n+1):
                graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])
    
    result = 0
    #각 학생을 번호에 따라 한 명씩 확인하며 도달 가능한지 체크
    for i in range(1, n+1):
        count = 0   
        for j in range(1, n+1):
            if graph[i][j] != INF or graph[j][i] != INF:
                count += 1
        if count == n:
            print(i , count)
            result += 1
        
    print(result)

def study_03():
    ###화성탐사
    #a->b는 b비용, b->a는 a비용
    #다익스트라 최단 경로 알고리즘
    import heapq
    import sys
    input = sys.stdin.readline
    INF = int(1e9)
    
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]
    
    n = 3
    graph = [[5,5,4], [3,9,1], [3,2,7]]

    #최단 거리 테이블을 모두 무한으로 초기화
    distance = [[INF] * n for _ in range(n)]
    
    x, y = 0,0 #시작 위치는 (0,0)
    #시작 노드로 가기 위한 비용은 (0,0) 위치의 값으로 설정하여, 큐에 삽입
    q = [(graph[x][y], x, y)]
    distance[x][y] = graph[x][y]
    
    #다익스트라 알고리즘 수행 
    while q:
        #가장 최단 거리가 짧은 노드에 대한 정보를 꺼내기
        dist, x, y = heapq.heappop(q)
        #현재 노드가 이미 처리도니 적이 있는 노드라면 무시
        if distance[x][y] < dist:
            continue
        #현재 노드와 연결된 다른 인접한 노드들을 확인
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            #맵의 범위를 벗어나는 경우 무시
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue
            cost = dist + graph[nx][ny]
            #현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
            if cost < distance[nx][ny]:
                distance[nx][ny] = cost
                heapq.heappush(q, (cost, nx, ny))
                
    print(distance)
    print(distance[n-1][n-1])

def study_04():
    ### 숨바꼭질
    #다익스트라 알고리즘을 이용하여 1번으로 부터 모든 노드로 최단 거리는 계산 
    #최단거리가 가장 긴 노드를 찾음
    data = [[3,6], [4,3], [3,2], [1,3], [1,2], [2,4], [5,2]]
    import heapq
    INF = int(1e9)
    
    #노드와 간선 갯수
    n ,m =6,7
    graph = [[] for i in range(n+1)]
    distance = [INF] * (n+1)
    
    for a,b in data:
        #a번 노드와 b번 노드의 이동 비용이 1이라는 의미 (양방향)
        graph[a].append((b,1))
        graph[b].append((a,1))
    
    #시작노드
    start = 1

    #다익스트라
    def dijkstra(start):
        q = []
        #시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
        heapq.heappush(q, (0, start))
        distance[start] = 0
        while q: #큐가 비어 있지않다면
            #가장 최단 거기가 짧은 노드에 대한 정보를 꺼내기
            dist, now = heapq.heappop(q)
            #현재 노드와 연결된 다른 인접한 노드들을 확인
            for i in graph[now]:
                cost = dist + i[1]
                #현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if cost < distance[i[0]]:
                    distance[i[0]] = cost
                    heapq.heappush(q, (cost, i[0]))
    
    #다익스트라 수행
    dijkstra(start)
    
    #최단 거리가 가장 먼 노드 번호
    max_node = 0
    #도달할 수 있는 노드 중에서, 최단 거리가 가장 먼 노드와의 최단 거리
    max_distance = 0
    #최단 거리가 가장 먼 노드와의 최단 거리와 동이한 최단 거리를 가지는 노드들의 리스트
    result = []
    
    for i in range(1, n+1):
        if max_distance < distance[i]:
            max_node = i
            max_distance = distance[i]
            result = [max_node]
        elif max_distance == distance[i]:
            result.append(i)
    
    print(max_node, max_distance, len(result))
    
study_04()