#탐색 : 만은 양의 데이터중에서 원하는 데이터를 찾는 과정
#자료구조 : 데이터를 표현하고 처리하는 방법
#스택 : 박스쌓기, 선입후출
#큐 : 대기줄, 선입선출
#DFS : 깊이우선탐색, 그래프를 탐색하는 알고리즘, 최대한 멀리 있는 노드를 우선으로 탐색하는 방식으로 스택 자료구조 이용
#BFS : 너비우선탐색, 가까운 노드부터 탐색하는 알고리즘, 큐를 이용, 인접한 노드를 반복적으로 큐에 넣도록 알고리즘을 작성하면 자연스럽게 먼저 들어온것이 먼저 나가게 되어 가까운 노드 부터 탐색

#deque 스택,큐 처럼 사용
# - 스택 : q.append()로 오른쪽 끝항목에 추가, q.pop() 오른쪽 끝항목을 가져오면서 삭제 
# - 큐 : q.appendleft() 왼쪽에 추가, q.pop() 오른쪽 끝항목 제거되면서 삭제
# - 반전 : q.reverse()

def study_01():
    ## 특정거리의 도시 찾기
    #BFS : 그래프에서 모든 간선 비용이 동일할때 너비우선탐색으로 최단거리를 찾음
    #x를 시작지점으로 모든 최단거리를 계산한 뒤에 각 최단거리를 하나씩 확인
    from collections import deque
    
    n, m, k, x = map(int, input().split())
    
    graph = [[] for _ in range(n+1)]
    
    #모든 도로 정보 저장
    for _ in range(m):
        a, b = map(int, input().split())
        graph[a].append(b)
    
    #모든 도시에 대한 최단 거리 초기화
    distance = [-1] * (n+1)
    distance[x] = 0 #출발 도시까지의 거리는 0으로 설정
    
    #BFS
    q = deque([x])
    while q:
        now = q.popleft()
        #현재 도시에서 이동할 수 있는 모든 도시를 확인
        for next_node in graph[now]:
            #아직 방문하지 않은 도시라면
            if distance[next_node] == -1:
                 #최단 거리 갱신
                 distance[next_node] = distance[now] + 1
                 q.append(next_node)
    
    #최단 거리가 k인 모든 도시의 번호를 오름차순으로 출력
    check = False
    for i in range(1, n+1):
        if distance[i] == k:
            print(i)
            check = True
    
    #최단거리가 없다면
    if check == False:
        print(-1)
    
def study_02():
    ##연구소
    # 모든 조합 계산시 파이썬 라이브러리나, DFS, BFS 이용
    # 벽의 개수가 3개가 되는 모든 조합을 찾은뒤 모두 계산
    
    # n,m = map(int, input().split())
    # data = [] #초기 맵 리스트
    # for _ in range(n):
    #     data.append(list(map(int, input().split())))
        
    n,m = 7,7
    data = [[2,0,0,0,1,1,0],
         [0,0,1,0,1,2,0],
         [0,1,1,0,1,0,0],
         [0,1,0,0,0,0,0],
         [0,0,0,0,0,1,1],
         [0,1,0,0,0,0,0],
         [0,1,0,0,0,0,0]]
    
    temp = [[0] * m for _ in range(n)] #벽을 설치한 뒤의 맵 리스트
    
    #4가지 이동 방향에 대한 리스트
    dx = [-1,0,1,0]
    dy = [0,1,0,-1]
    
    result = 0
    
    #깊이 우선 탐색(DFS)를 이용해 각 바이러스가 사방으로 퍼지도록 하기
    def virus(x,y):
        for i in range(4):
            nx = x + dx[i]    
            ny = y + dy[i]
            
            #상,하, 좌, 우 중에서 바이러스가 퍼질 수 있는 경우
            if nx >= 0 and nx < n and ny >= 0 and ny < m:
                if temp[nx][ny] == 0:
                    #해당 위치에 바이러스 배치하고, 다시 재귀적으로 수행
                    temp[nx][ny] = 2
                    virus(nx, ny)
    
    #현재 맵에서 안전 영역의 크기 계산하는 메서드
    def get_score():
        score = 0   
        for i in range(n):
            for j in range(m):
                if temp[i][j] == 0:
                    score += 1
        return score

    #깊이 우선 탐색을 이용해 울타리를 설치하면서, 매번 안전 영역의 크기 계산
    def dfs(count):
        nonlocal result 
        #울타리가 3개 설치된경우
        if count == 3:
            for i in range(n):
                for j in range(m):
                    temp[i][j] = data[i][j]
            #각 바이러스의 위치에 전파 진행
            for i in range(n):
                for j in range(m):
                    if temp[i][j] == 2:
                        virus(i,j)
            #안전 영역의 최댓값 계산
            result = max(result, get_score())
            return #재귀함수 안에만 return 
        #빈 공간에 울타리 설치
        for i in range(n):
            for j in range(m):
                if data[i][j] == 0:
                    data[i][j] = 1
                    count += 1
                    dfs(count) #count 3일때 return으로 아래 코드 진행
                    data[i][j] = 0  
                    count -= 1
    dfs(0)
    print(result)
    
    
def study_03():
    #경재적 전염
    import copy
    n, k = map(int, input().split())
    data = []
    info = [] ## 책풀이용
    graph = [] ## 책풀이용 #전체 보드 정보를 담는 리스트
    for i in range(n):
        graph.append(list(map(int, input().split())))
        for j in range(n):
            #해당 위치에 바이러스가 존재하는 경우
            if graph[i][j] != 0:
                #(바이러스 종류, 시간, 위치 x,y)삽입
                info.append((graph[i][j], 0, i ,j))
        
        
    data = copy.deepcopy(graph)
    s, x, y = map(int, input().split())
    
    def virus_add(data, x, y):
        dx = [-1, 0, 1 ,0]
        dy = [0, 1, 0, -1]
        val = data[x][y]
        
        for i in range(4):
            nx = dx[i] + x
            ny = dy[i] + y
            if nx >= 0 and nx < n and ny >= 0 and ny < n:
                if data[nx][ny] == 0:
                    data[nx][ny] = val    
    
    for i in range(s):
        for v in range(1, k+1): 
            temp = copy.deepcopy(data) #원본 영향X
            #증가
            for j in range(n):
                for k in range(n):
                    if temp[j][k] == v:
                        virus_add(data, j, k)
                        print("==================")
                        print("초 : ", i)
                        print("번호 : ", v)
                        print("data :", data)
    
    print(data[x-1][y-1])
    
    
    ### 책풀이
    #너비 우선탐색
    #큐에 낮은 바이러스부터 저장
    from collections import deque
    info.sort()
    q = deque(info)
    
    dx = [-1, 0, 1, 0]
    dy = [0 ,1 ,0 ,-1]
    
    #너비 우선 탐색 def
    while q:
        virus, a, b, c = q.popleft()
        #정확히 s초가 지나거나, 큐가 빌때까지 반복
        if a == s:
            break 
        #현재 노드에서 주변 4가지 위치를 각각 확인
        for i in range(4):
            nx = dx[i] + b
            ny = dy[i] + c
            if nx >= 0 and nx < n and ny >= 0 and ny < n:
                #아직 방문하지 않은 위치라면, 그 위치에 바이러스 넣기
                if graph[nx][ny] == 0:
                    graph[nx][ny] = virus            
                    q.append((virus, a+1, nx, ny))
    print(graph[x-1][y-1])
    
#실행
study_03()