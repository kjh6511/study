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
    
    n, m = 6,6 #학생수, 비교횟수

    data = [[1,5], [3,4], [4,2], [4,6], [5,2], [5,4]]
    
    graph = [ [0] * (n+1) for _ in range(n+1)]
    
    
    def find_node(graph, count, x, y):
        if graph[x][y] == 0 or count ==  :
            return count
    
    for i in data:
        graph[i[0]][i[1]] = 1
        
        for k in range(1, n+1):
            count = 0
            for a in range(1, n+1):
                if graph[k][a] != 0:
                    count += 1
                    
        
    
    
       

study_02()