#### 그래프 이론
# 서로 다른 개체(객체)가 연결되어 있다면 '그래프 알고리즘'
# 인접행렬 : 2차원 배열을 사용하는 방식
# 인접리스트 : 리스트를 사용하는 방식
# 서로소 집합 : 공통원소가 없는 두 집합
# 서로소 집합 자료구조 : union과 find 2개 연산으로 조작, union-find 자료구조, 트리 자료구조 이용 
# union(합집합) 연산 : 2개의 원소가 포함된 집합을 하나의 집합으로 합치는 연산
# find(찾기) 연산 : 특정한 원소가 속한 집하이 어떤 집합인지 알려주는 연산

def study_01():
    #기본적인 서로소 집합 알고리즘
    
    #특정 원소가 속한 집합을 찾기
    def find_parent(parnet, x):
        #루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if parnet[x] != x:
            return find_parent(parnet, parnet[x])
        return x

    #두 원소가 속한 집합을 합치기
    def union_parnet(parnet, a, b):
        a = find_parent(parnet,a)
        b = find_parent(parnet,b)
        if a < b:
            parnet[b] = a
        else:
            parnet[a] = b
    
    #노드의 개수와 간선(union 연산)의 개수 입력 받기
    v, e = map(int, input().split())
    parent = [0] * (v+1) #부모 테이블 초기화
    
    #부모 테이블상에서 부모를 자기 자신으로 초기화
    for i in range(1, v+1):
        parent[i] = i
    
    #union 연산을 각각 수행
    for i in range(e):
        a, b = map(int, input().split())
        union_parnet(parent,a,b)
        
    #각 원소가 속한 집합 출력
    print('각 원소가 속한 집합: ', end='')
    for i in range(1, v+1):
        print(find_parent(parent,i), end=' ')
    print()
    
    #부모 테이블 내용 출력
    print('부모 테이블: ',end='')
    for i in range(1, v+1):
        print(parent[i], end=' ')
    
def study_02():
    ###개선된 서로소 집합 알고리즘
    
    #루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
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
    
    #노드의 개수와 간선(union 연산)의 개수 입력 받기
    v, e = map(int, input().split())
    parent = [0] * (v+1) #부모 테이블 초기화
    
    #부모 테이블상에서 부모를 자기 자신으로 초기화
    for i in range(1, v+1):
        parent[i] = i
    
    #union 연산을 각각 수행
    for i in range(e):
        a, b = map(int, input().split())
        union_parnet(parent,a,b)
        
    #각 원소가 속한 집합 출력
    print('각 원소가 속한 집합: ', end='')
    for i in range(1, v+1):
        print(find_parent(parent,i), end=' ')
    print()
    
    #부모 테이블 내용 출력
    print('부모 테이블: ',end='')
    for i in range(1, v+1):
        print(parent[i], end=' ')

def study_03():
    ### 서로서 집합을 활용한 사이틀 판별
    # 간선에 방향성이 없는 무향 그래프에서만 적용 가능
        #루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
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
    
    #노드의 개수와 간선(union 연산)의 개수 입력 받기
    v, e = map(int, input().split())
    parent = [0] * (v+1) #부모 테이블 초기화
    
    #부모 테이블상에서 부모를 자기 자신으로 초기화
    for i in range(1, v+1):
        parent[i] = i
        
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
        
    if cycle:
        print("사이클이 발생했습니다.")
    else:
        print("사이클이 발생하지 않았습니다.")
    
### 신장 트리 : 하나의 그래프가 있을 때 모든 노드를 포함하면서 사이클이 존재하지 않는 부분 그래프를 의미 
# 크루스칼 알고리즘 : 대표적인 최소 신장 트리 알고리즘 (그리디 알고리즘)
# 신장 트리에 포함되는 간선의 개수가 '노드의 개수 -1'
# 1. 간선정보를 정렬한다(노드 데이터 순서에 따라 정렬) -> (1,2) (1,5) (2,3) ...
# 2. 비용이 가장 짧은 간선은 선택하고 union 수행
# 3. 계속해서 비용이 작은 노드를 선택하고 반복한다, 이미 포함된 노드이면 호출하지 않는다.
# 4. 모두 완료후 비용을 모두 더하면 총 비용을 구할 수 있다.
def study_04():
    #크루스칼 알고리즘
    
    #특정 원소가 속한 집합을 찾기    
    def find_parent(parent, x):
        #루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if parent[x] != x:
            parent[x] = find_parent(parent, parent[x])
        return parent[x]
    
    #두 원소가 속한 집합을 합치기
    def union_parent(parent,a ,b):
        a = find_parent(parent, a)
        b = find_parent(parent, b)
        if a < b:
            parent[b] = a
        else:
            parent[a] = b
    
    # 노드의 개수와 간선의 갯수를 받기
    v, e = map(int, input().split())
    parent = [0] * (v+1) #부모 테이블 초기화
    
    #모든 간선을 담을 리스트와 최종 비용을 담을 변수
    edges = []
    result = 0
    
    #부모 테이블 상에서, 부모를 자기 자신으로 초기화
    for i in range(1, v+1):
        parent[i] = i
    
    #모든 간선에 대한 정보를 입력받기
    for _ in range(e):
        a, b, cost = map(int, input().split())
        #비용순으로 정렬하기 위해서 듀플의 첫 번째 원소를 비용으로 설정
        edges.append((cost,a,b))
    
    #간선을 비용순을 정렬
    edges.sort() 
    
    #간선을 하나씩 확인하며
    for edge in edges:
        cost, a, b = edge
        #사이클이 발생하지 않는 경우에만 집합에 포함
        if find_parent(parent, a) != find_parent(parent, b):
            union_parent(parent, a, b)
            result += cost
    
    print(result)

### 위상정렬 : 순서가 정해져 있는 일련의 작업을 차례대로 수행해야 할 때 사용할 수 있는 알고리즘
# 방향 그래프의 모든 노드를 방향성에 거스르지 않도록 순서대로 나열하는 것    
# 1. 노드1만 진입차수 0이기때문에 큐에 노드 1만 삽입
# 2. 큐에서 노드1을 꺼내면서 1과 연결된 간선 제거하여 다음 노드들을 집입차수0으로하여 큐에 삽입
# 3. 계속 반복한다, 진입차수가 0이되지않으면 넘어간다
# 4. 큐에서 빠져나간 노드 순서대로 출력하면 위상정렬  
def study_05():
    ## 위상정렬
    from collections import deque
    
    #노드의 개수와 간선의 개수를 입력 받기
    v,e = map(int, input().split())
    #모든 노드에 대한 진입차수는 0으로 초기화
    indegree = [0] * (v+1)
    #각 노드에 연결된 간선 정보를 담기 위한 연결 리스트(그래프) 초기화
    graph = [[] for i in range(v+1)]
    
    #방향 그래프의 모든 간선 정보를 입력받기
    for _ in range(e):
        a, b = map(int, input().split())
        graph[a].append(b) #정점 A에서 B로 이동 가능
        #진입차수를 1증가
        indegree[b] += 1
    
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
    
    topology_sort()
                
#### 실전문제
def study_06():
    ## 팀결정
    # 서로소 집합 알고리즘 
    
    #특정 원소가 속한 집 합을 찾기
    def find_parent(parent, x):
        #루트 노드가 아니라면, 루트 노드를 찾을 떄까지 재귀적으로 호출
        if parent[x] != x:
            parent[x] = find_parent(parent, parent[x])
        return parent[x]

    # 두원소가 속한 집합을 합치기
    def union_parent(parent, a, b):
        a = find_parent(parent, a)
        b = find_parent(parent, b)
        if a < b:
            parent[b] = a
        else:
            parent[a] = b
    
    n,m = map(int, input().split())
    parent = [0] * (n+1)
    
    for i in range(0, n+1):
        parent[i] = i
    
    #각 연산을 하니씩 확인
    for i in range(m):
        oper, a, b = map(int, input().split())
        #합집합(union) 연산인 경우
        if oper == 0:
            union_parent(parent, a, b)
        #찾기(find) 연산인 경우
        elif oper == 1:
            if find_parent(parent, a) == find_parent(parent, b):
                print('YES')
            else:
                print('NO')


def study_07():
    ## 도시분할계획
    #전체 그래프에서 2개의 최소 신장트리를 만들어야한다
    #크루스칼 알고리즘으로 최소 신장 트리를 찾은 뒤에 최소 신장 트리를 구성하는 간선 중에서 가장 비용이 큰 간선을 제거하는 것
    
    #특정 원소가 속한 집합을 찾기
    def find_parent(parent, x):
        if parent[x] != x:
            parent[x] = find_parent(parent, parent[x])
        return parent[x]

    #두원소 합치기
    def union_parent(parent, a, b):
        a = find_parent(parent,a)
        b = find_parent(parent,b)
        if a < b:
            parent[b] = a
        else:
            parent[a] = b
    
    #입력
    v, e = map(int, input().split())
    parent = [0] * (v+1) #부모 테이블 초기화
    
    #모든 간선을 담을 리스트와 최종 비용을 담을 변수
    edges = []
    result = 0
    
    for i in range(1, v+1):
        parent[i] = i
    
    #모든 간선에 대한 정보를 입력
    for _ in range(e):
        a, b, cost = map(int, input().split())
        #비용순으로 정렬하기 위해서 듀플의 첫번쨰원소를 비용으로 설정
        edges.append((cost,a,b))
    
    #간선을 비용순으로 정렬
    edges.sort()
    last = 0 #최소 신장 트리에 포함되는 간선 중에서 가장 비용이 큰 간선
    
    #간선을 하니씩 확인하며
    for edge in edges:
        cost,a,b = edge
        #사이클이 발생하지 않는 경우에만 집합에 포함
        if find_parent(parent,a) != find_parent(parent,b):
            union_parent(parent,a,b)
            result += cost
            last = cost
            
    print(result - last)    


def study_08():
    ## 커리큘럼
    # 위상정렬 알고리즘
    # deepcopy() : 리스트 복사

    from collections import deque
    import copy
    
    v = int(input())
    
    #모든 노드에대한 집입차수는 0으로 초기화
    indegree = [0] * (v+1)
    #각 노드에 연결된 간선 정보를 담기 위한 연결 리스트(그래프) 초기화
    graph = [[] for i in range(v+1)]
    #각 강의 시간을 0으로 초기화
    time = [0] *(v+1)
    
    #방향 그래프의 모든 간선 정보를 입력받기
    for i in range(1, v+1):
        data = list(map(int, input().split()))
        time[i] = data[0] #첫번째 수는 시간 정보를 담고 있음
        for x in data[1:-1]:
            indegree[i] += 1
            graph[x].append(i)
    
    #위상 정렬 함수
    def topology_sort():
        result = copy.deepcopy(time) #알고리즘 수행 결과를 담을 리스트
        q = deque() #큐 기능을 위한 deque 라이브러리 사용
        
        #처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
        for i in range(1, v+1):
            if indegree[i] == 0:
                q.append(i)
        
        while q: #큐가 빌때까지 반복
            #큐에서 원소 꺼내기
            now =q.popleft()
            #해당 원소와 연결된 노드들의 집입차수에서 1빼기
            for i in graph[now]:
                result[i] = max(result[i], result[now] + time[i])
                indegree[i] -= 1
                #새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                if indegree[i] == 0:
                    q.append(i)
        
        #위상 정렬을 수행한 결과 출력
        for i in range(1, v+1):
            print(result[i])
            
    topology_sort()
            
    
    
    
####### 실행용
study_08()
