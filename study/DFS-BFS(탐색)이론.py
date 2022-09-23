#### DFS/BFS : 그래프를 탐색
#탐색 알고리즘 
#삽입(push), 삭제(pop)
#오버플로: 수용할 수 있는 데이터 크기가 꽉찬 상태에서 삽입연산을 수행할때 발생
#언더플로: 데이터가 전혀 들어 있지 않은 상태에서 삭제를 수행할때 발생

from unittest import result


def study_01():        
    #스택: 박스쌓기 아래에서 위로쌓음, 선입후출 구조, 후입선출 구조
    stack = []
    stack.append(5)
    stack.append(2)
    stack.append(3)
    stack.append(7)
    stack.pop()
    stack.append(1)
    stack.append(4)
    stack.pop()
    print(stack) #최하단의 원소부터 출력 [5,2,3,1]
    print(stack[::-1]) #최상단 원소부터 출력 [1,3,2,5]
    
    #큐 queue: 대기줄, 먼저온 사람이 먼저, 선입선출 구조
    from collections import deque
    queue = deque()
    queue.append(5)
    queue.append(2)
    queue.append(3)
    queue.append(7)
    queue.popleft()
    queue.append(1)
    queue.append(4)
    queue.popleft()
    print(queue) #먼저 들어온 순서대로 deque([3,7,1,4]) , [5,2]삭제
    queue.reverse() #역순
    print(queue) #나중에 들어온 원소부터 deque([4,1,7,3])
    
    
    #####재귀함수
    #팩토리얼 (반복적 구현)
    def factorial_iterative(n):
        result = 1
        for i in range(1, n+1): #1부터 n까지 곱하기
            result *= i
        return result    
    #팩토리얼 (재귀적 구현)
    def factorial_recursive(n):
        if n <= 1:
            return 1
        return n * factorial_recursive(n-1)

def study_02():
    #DFS(Depth-First Search) : 깊이우선 탐색, 그래프의 깊은 부분을 우선 탐색, 스택 사용, 재귀함수 이용
    #인접 행렬(Adjacency Matrix) : 2차원 배열로 그래프의 연결 관계를 표현하는 방식, 노드가 연결된 형태를 기록하는 방식
    INF = 999999999 #무한비용선언
    graph = [       #2차원 리스트를 이용해 인접 행렬 표현
        [0,7,5],
        [7,0,INF],
        [5,INF,0]
    ]
    #인접 리스트(Adjacency List) : 리스트로 그래프의 연결 관계를 표현하는 방식 
    #행(Row)이 3개인 2차원 리스트로 인접리스트 표현
    graph = [[] for _ in range(3)]
    #노드 0에 연결된 노드 정보 저장(노드,거리)
    graph[0].append((1,7)) 
    graph[0].append((2,5)) 
    #노드 1에 연결된 노드 정보
    graph[1].append((0,7))
    #노드2에 연결된 노드 정보
    graph[2].append((0,5))
    print(graph) #[[(1,7),(2,5)], [(0,7)], [(0,5 )]]
    
    ##DFS
    def dfs(graph, v, visited):
        #현재 노드를 방문 처리
        visited[v] = True
        print(v, end=' ')
        #현재 노드와 연결된 다른 노드를 재귀적으로 방문
        for i in graph[v]:
            if not visited[i]:
                dfs(graph, i, visited)
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
    #정의된 DFS 함수 호출
    dfs(graph, 1, visited) # 1 2 7 6 8 3 4 5
    
    
    ##BFS (Breadth First Search): 너비 우선 탐색, 가까운 노드부터 탐색하는 알고리즘, 큐 사용, 큐 자료구조 이용
    # (코딩테스트에서는 보통 DFS보다 빠름)
    from collections import deque
    def bfs(graph, start, visited):
        #큐 구현을 위해 deque 라이브러리 사용
        queue = deque([start])
        #현재 노드를 방문처리
        visited[start] = True
        #큐가 빌 때까지 반복
        while queue:
            #큐에서 하나의 원소를 뽑아 출력
            v = queue.popleft()
            print(v, end=' ')
            #해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
            for i in graph[v]:
                if not visited[i]:
                    queue.append(i)
                    visited[i] = True        
    #각 노드가 연결된 정보를 리스트 자료형으로 표현(2차원 리스트)
    graph = [
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
    #정의된 BFS함수 호출
    bfs(graph, 1, visited) # 1 2 7 6 8 3 4 5 1 2 3 8 7 4 5 6

    
##실전문제
#음료수 얼려 먹기
def study_03():
    #nxm이 연결된0과 막힌1로 채워진 얼음판, 얼음이 몇개 구해지는가
    #DFS활용 상하좌우를 0,1인지 확인하고 방문체크하기
    
    n,m = map(int, input().split()) #공백으로 구분하여 입력받기
    #2차원 리스트의 맵 정보 입력받기
    graph = [] 
    for i in range(n):
        graph.append(list(map(int, input()))) #list는 []한개 [[],[], ...]

    #dfs로 특정한 노드를 방문한 뒤에 연결도니 모든 노드들도 방문
    def dfs(x,y):
        #주어진 범위를 벗어나는 경우에는 즉시 종료
        if x <= -1 or x >= n or y <= -1 or y >= m:
            return False
        #현재 노드를 아직 방문하지 않았다면
        if graph[x][y] == 0:
            #해당 노드 방문 처리
            graph[x][y] = 1
            #상, 하, 좌, 우의 위치도 모두 재귀적으로 호출
            dfs(x-1, y)
            dfs(x, y-1)
            dfs(x+1, y)
            dfs(x, y+1)
            return True
        return False
    #모든 노드(위치)에 대하여 음료수 채우기
    result = 0
    for i in range(n):
        for j in range(m):
            #현재 위치에서 DFS 수행
            if dfs(i,j) == True:
                result += 1
    print(result) #            

##미로탈출
def study_04():
    #현재위치는(1,1)이며 출구는 (N,M)으로 괴물이 있는부분은 0, 없는 부분은 1로 연결된 미로로만 탈출
    #움직여야하는 최소 칸의 갯수는(시작과 끝칸도 더함)
    # 4 <= n , m <= 200
    #BFS가 효과적 시작지점에서 가까운 노드부터 차례로 모든 노드를 탐색하기 떄문 
    from collections import deque
    
    n, m = map(int, input().split())
    #2차원 리스트의 앱 정보 입력받기
    graph = []
    for i in range(n):
        graph.append(list(map(int,input())))
    #이동할 네 방향 정의(상, 하, 좌, 우)
    dx = [-1,1,0,0]
    dy = [0,0,-1,1]
    
    #BFS 소스코드 구현
    def bfs(x,y):
        #deque 라이브러리 사용
        queue = deque()
        queue.append((x,y))
      
        #큐가 빌 때까지 반복
        while queue:
            x,y = queue.popleft() #pop(0)
            #현재 위치에서 네 방향으로의 위치 확인
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                #미로 찾기 공간을 벗어난 경우 무시
                if nx < 0 or ny < 0 or nx >= n or ny >= m:
                    continue
                #벽인 경우 무시
                if graph[nx][ny] == 0:
                    continue
                #해당 노드를 처음 방문하는 경우에만 최단 거리 기록
                if graph[nx][ny] == 1:
                    graph[nx][ny] = graph[x][y] + 1 #찾은곳에 기준값 더하기 
                    queue.append((nx,ny)) #찾은 미로방향 조사 반복
        #가장 오른쪽 아래까지의 최단 거리 반환
        return graph[n-1][m-1]
    print(bfs(0,0))            
        
        
        
####### 실행용
study_04()
