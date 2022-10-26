### 그래프 문제
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

from re import A


def study_01():
    ##여행 계획
    #서로소 집합 알고리즘 
    #여행 계획에 해당 하는 모든 노드가 같은 집합에 속하기만 하면 가능한 여행 경로 라는 것
    #두 노드 사이에 도로가 존재하는 경우 union연산을 이용해 서로 연결된 두 노드를 같은 집합에 속하도록 만든다.
    
    n, m = 5,4 #여행지 수, 여행계획에 속한 도시수
    
    data = [[0,1,0,1,1], [1,0,1,1,0], [0,1,0,0,0], [1,1,0,0,0], [1,0,0,0,0]]
    trip = [2,3,4,3]
    
    #특정 원소가 속한 집합을 찾기
    def find_parent(parent, x):
        #루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if parent[x] != x:
            parent[x] = find_parent(parent, parent[x])
        return parent[x]

    #두 원소가 속한 집합을 합치기
    def union_parent(parent,a,b):
        a = find_parent(parent,a)
        b = find_parent(parent,b)
        if a < b:
            parent[b] = a
        else:
            parent[a] = b
    
    #여행지의 개수와 여행 계획에 속한 여행지의 개수 입력받기
    #부모테이블 초기화
    parent = [0] * (n+1)
    
    #부모 테이블상에서 부모를 자기 자신으로 초기화
    for i in range(1, n+1):
        parent[i] = i
    
    #union 연산을 각각 수행
    for i in range(n):
        data_list = data[i]
        for j in range(n):
            if data_list[j] == 1: #연결된 경우 union 연산 수행
                print("====================")
                print("i : ", i)
                print("j : ", j)
                print("parent : ", parent)
                union_parent(parent, i+1, j+1)
                print("parent after : ", parent)
    
    
    result = True
    #여행 계획에 속하는 모든 노드의 루트가 동일한지 확인
    for i in range(m-1):
        if find_parent(parent, trip[i]) != find_parent(parent, trip[i+1]):
            result = False
    
    #여행 계획에 속하는 모든 노드가 서로 연결되어 있는지(루트가 동일한지) 확인
    if result:
        print("YES")
    else:
        print("NO")
    
def study_02():
    ### 탑승구
    #서로소 집합 알고리즘
    #모든 게이트를 사용하면 0으로 루트 연결
    
    
    g, p = 4, 3 #탑승구수, 비행기수
    i_list = [4,1,1]
    
    #특정 원소가 속한 집합 찾기
    def find_parent(parent, x):
        print("find - parent : " ,parent)
        print("x : " ,x)
        #루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if parent[x] != x:
            parent[x] = find_parent(parent, parent[x])
        return parent[x]
    
    #두원소가 속한 집합을 합치기
    def union_parnet(parent, a, b):
        print("uni _ parent: " , parent)
        print("uni _ a: " , a)
        print("uni _ b: " , b)
        a = find_parent(parent, a)
        b = find_parent(parent, b)
        print("a: " , a)
        print("b: " , b)
        if a < b:
            parent[b] = a
        else:
            parent[a] = b
        print("uni2 _ parent: " , parent)
    
    #부모 테이블 초기화 및 부모를 자기 자신으로 초기화
    parent = [i for i in range(g+1)]

    result = 0
    for i in range(p):
        print("=============")
        print("i :", i)
        data = find_parent(parent, i_list[i]) #현재 비행기의 탑승구의 루트 확인
        print("data :", data)
        if data == 0: #현재 루트가 0이라면 종료
            break
        union_parnet(parent, data, data-1) #그렇지 않다면 바로 왼쪽의 집합과 합치기
        result += 1
    print(result)
        

def study_03():
    ## 어두운 길
    #최소 신장 트리
    #임의의 두 집에 대하여 가로등이 켜진 도로만으로 오갈수 있도록 -> 최소 신장 트기
    #크루스칼 알로리즘
    
    n, m = 7, 11 #집갯수, 도로갯수
    
    data = [[0,1,7],[0,3,5], [1,2,8], [1,3,9], [1,4,7], [2,4,5], [3,4,15], [3,5,6], [4,5,8], [4,6,9], [5,6,11]]
    
    def find_parent(parent, x):
        if parent[x] != x:
            parent[x] = find_parent(parent, parent[x])
        return parent[x]
    
    def union_parent(parent, a, b):
        a = find_parent(parent, a)
        b = find_parent(parent, b)
        if a < b:
            parent[b] = a
        else:
            parent[a] = b
    
    parent = [ i for i in range(n+1)]
    
    #모든 간선을 담을 리스트와 최종 비용을 담을 변수
    edges = []
    result = 0
    
    for x,y,z in data:
        #비용순으로 정렬하기 위해서 듀플의 첫 번째 원소를 비용으로 설정
        edges.append((z,x,y))
    edges.sort()
    
    #전체 가로등 비용
    total = 0
    
    #간선을 하나씩 확인하며
    for edge in edges:
        cost, a, b = edge
        total += cost
        #사이클이 발생하지 않는 경우에만 집합에 포함
        if find_parent(parent,a) != find_parent(parent, b):
            union_parent(parent, a,b)
            result += cost
    print(parent)
    print(total - result)

def study_04():
    ## 행성터널
    #최소신장트리
    #크루스칼 알고리즘
    def find_parnet(parent,x):
        if parent[x] != x:
            parent[x] = find_parnet(parent, parent[x])
        return parent[x]
    
    

study_03()