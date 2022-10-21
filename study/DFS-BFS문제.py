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
    
    
def study_04():
    #괄호
    from collections import deque
    data = []
    n = input()
    q = n
    check = 0

    ##가장 마지막 괄호 찾기
    while check < len(n):
            
        if n[check] == '(' and n[check+1] == ')':
            check += 1
        else:    
            data.append(check)
        check += 1
        if check == len(n)-1:
            if n[check-1] != '(' and n[check] != ')':
                data.append(check)
            break
    
    n = list(n)
    for i in range(len(data)//2):
        n[data[i]] = "("
        n[data[len(data)-i-1]] = ")"
    
    n = ''.join(n)
        
    print(n)
            
    ##책풀이
    #재귀로 푼다
    
    def balanced_index(p):
        count = 0 #왼쪽 괄호의 개수
        for i in range(len(p)):
            if p[i] == '(':
                count += 1
            else : 
                count -= 1
            if count == 0:
                return i
    
    #올바른 괄호 문자열 인지 판단
    def check_proper(p):
        count = 0 #왼쪽 괄호의 개수
        for i in p:
            if i == '(':
                count += 1
            else:
                if count == 0: #쌍이 맞지 않는 경우에 False 반환
                    return False
                count -= 1
        return True #쌍이 맞는 경우 True 반환
    
    def solution(p):
        answer = ''
        if p == '':
            return answer
        index = balanced_index(p)
        u = p[:index + 1]
        v = p[index + 1:]
        #올바른 괄호 문자열이면, v에 대해 한수를 수행한 결과를 붙여 반환
        if check_proper(u):
            answer = u + solution(v)
        #올바른 괄호 문자열이 아니라면 아래의 과정을 수행
        else:
            answer = '('
            answer += solution(v)
            answer += ')'
            u = list(u[1:-1]) #첫번째와 마지막 문자 제거
            for i in range(len(u)):
                if u[i] == '(':
                    u[i] = ')'
                else:
                    u[i] = '('
            answer += "".join(u)
        return answer
    
    print(solution(q))
                     
        
def study_05():
    #연산자 끼워 넣기
    n = int(input())
    a = list(map(int, input().split()))
    x = list(map(int, input().split()))
    
    
    #연산자 리스트에 나열
    data = []
    info = ['+','-','*','//']
    for i in range(len(x)):
        for _ in range(x[i]):
            data.append(info[i])
    
    from itertools import permutations
    #연산자 순열
    per =  list(permutations(data, n-1))
    
    min_num = int(1e9)
    max_num = 0
    
    for p in per:
        sum_num = a[0]
        for k in range(1, len(a)):
            if p[k-1] == '+':
                sum_num += a[k]
            elif p[k-1] == '-':
                sum_num -= a[k]
            elif p[k-1] == '*':
                sum_num *= a[k]
            elif p[k-1] == '//':
                if sum_num < 0:
                    sum_num = abs(sum_num)
                    sum_num //= a[k]
                    if sum_num != 0:
                        sum_num *= -1
                else:    
                    sum_num //= a[k]
                        
        if sum_num > max_num:
            max_num = sum_num
        if sum_num < min_num:
            min_num = sum_num
    print(max_num)
    print(min_num)

    ##책풀이
    ##순열 라이브러리 없이 DFS탐색으로 
    
    add, sub, mul, div = x[0], x[1], x[2], x[3]
    
    min_value = 1e9
    max_vaule = -1e9 
    
    #깊이 우선 탐색 DFS 메서드
    def dfs(i, now):
        nonlocal min_value, max_vaule, add, sub, mul, div

        #모든 연산자를 다 사용한 경우, 최솟값과 최대값 업데이트
        if i == n:
            min_value = min(min_value, now)
            max_vaule = max(max_vaule, now)
        else:
            #각 연산자에 대하여 재귀적으로 수행
            if add > 0:
                add -= 1
                dfs(i+1, now + a[i])
                add += 1    
            if sub > 0:
                sub -= 1
                dfs(i+1, now - a[i])
                sub += 1
            if mul > 0:
                mul -= 1
                dfs(i+1, now * a[i])
                mul += 1
            if div > 0:
               div -= 1
               dfs(i+1, int(now / a[i]))
               div += 1
        
    dfs(1, a[0])   
    print(max_vaule) 
    print(min_value)
    
def study_06():
    ##감시 피하기
    n = int(input())
    data = []
    t_list = []
    s_list = []
    
    for i in range(n):
        a = list(input().split())
        data.append(a)
        for j in range(n):
            if a[j] == 'T':
                t_list.append([i,j])
            elif a[j] == 'S':
                s_list.append([i,j])
                
                
    def check_distance(data, t_list):
        result_list = []
        dx = [-1,0,1,0]
        dy = [0,1,0,-1]
        check = True
        #각 선생님당 볼수 있는 곳 체크
        for t in t_list:
            for i in range(4): #방향 변화
                for k in range(1, n):    
                    nx = (dx[i]*k)+ t[0]
                    ny = (dy[i]*k)+ t[1] 
                    if nx >= n or ny >= n or nx < 0 or ny < 0:
                        break
                    elif data[nx][ny] == "O":
                        break
                    elif data[nx][ny] == "S":
                        check = False 
                        break
                    else:
                        if data[nx][ny] == "X" and [nx, ny] not in result_list:
                            result_list.append([nx, ny])  
        return result_list, check
    
    
    #선생님이 갈수 있는 곳 체크
    op, check = check_distance(data, t_list)

    #조합
    from itertools import combinations
    com = list(combinations(op,3))
    
    result = "NO"
    
    import copy
    #조합적용
    for c in com:
        data_copy = copy.deepcopy(data)
        for v in c:
            data_copy[v[0]][v[1]] = "O"
        
        #선생님 체크  
        tc, check = check_distance(data_copy, t_list)
        if check == True:
            result = "YES"
        
    print(result)   
        
    ### 책풀이 

    board = [] #복도 정보
    teachers = [] #선생님 위치 정보
    spaces = [] # 모든 빈 공간 위치 정보
    
    for i in range(n):
        board.append(data[0])
        for j in range(n):
            #선생님이 존재하는 위치 저장
            if board[i][j] == 'T':
                teachers.append((i,j))
            #장애물을 설치할 수 있는 (빈공간) 위치 저장
            if board[i][j] == 'X':
                spaces.append((i,j))
    
    #특정 방향으로 감시를 진행 (학생 발견: True)
    def watch(x,y,direction):
        #왼쪽 방향으로 감시
        if direction == 0:
            while y >= 0:
                if board[x][y] == 'S': #학생이 있는 경우
                    return True        
                if board[x][y] == 'O': #장애물이 있는 경우
                    return False
                y -= 1
        #오른쪽 방향으로 감시
        if direction == 1:
            while y < n:
                if board[x][y] == 'S': #학생이 있는 경우
                    return True        
                if board[x][y] == 'O': #장애물이 있는 경우
                    return False
                y += 1
        #위쪽 방향으로 감시
        if direction == 2:
            while x >= 0:
                if board[x][y] == 'S': #학생이 있는 경우
                    return True        
                if board[x][y] == 'O': #장애물이 있는 경우
                    return False
                x -= 1
        #아래쪽 방향으로 감시
        if direction == 3:
            while x < n:
                if board[x][y] == 'S': #학생이 있는 경우
                    return True        
                if board[x][y] == 'O': #장애물이 있는 경우
                    return False
                x += 1
        return False
    #장애물 설치 이후에, 한 명이라도 학생이 감지되는지 검사
    def process():
        #모든 선생님의 위치를 하나씩 확인
        for x, y in teachers:
            #4가지 방향으로 확인
            for i in range(4):
                if watch(x,y,i):
                    return True
        return False
    
    find = False #학생이 한 명도 감지되지 않도록 설치할 수 있는지의 여부
    
    #빈공간에 3개를 뽀는 모든 조합 확인
    for data in combinations(spaces,3):
        #장애물 설치
        for x, y in data:
            board[x][y] = 'O'
        #학생이 한명도 감지되지 않는 경우
        if not process():
            find = True
            break
        #설치된 장애물을 다시 없애기
        for x, y in data:
            board[x][y] = "X"
    
    if find:
        print('YES')
    else:
        print('NO')

def study_07():
    ##인구 이동
    
    n ,l, r = map(int, input().split())
    
    import copy
    data = []
    for _ in range(n):
        data.append(list(map(int, input().split())))
    graph = copy.deepcopy(data)

    dx = [-1,0,1,0]
    dy = [0,-1,0,1]
    
    move_count=0
    for i in range(n):
        for j in range(n):
            check = True
            total_sum = data[i][j]
            total_count = 1
            #4방향으로 확인하기
            for k in range(4):
                nx = i + dx[k]
                ny = j + dy[k]
                if nx < 0 or nx >=n or ny < 0 or ny >=n:
                    continue
                else:
                    sub = abs(data[i][j] - data[nx][ny])
                    if  sub >= l and sub <= r:
                        total_sum += data[nx][ny]
                        total_count += 1
                    else:
                        check = False
                        break
            if check == True:
                move_count += 1
                total = total_sum // total_count
                data[i][j] = total
                for a in range(4):
                    ax = i + dx[a]
                    ay = j + dy[a]
                    if ax < 0 or ax >=n or ay < 0 or ay >=n:
                        continue
                    else:
                        data[ax][ay] = total
            
    print(move_count)        
            
    ## 책풀이
    
    result = 0
    #특정 위치에서 출발하여 모든 연합을 체크한 뒤에 데이터 갱신
    def process(x,y,index):
        from collections import deque
        #(x,y)의 위치와 연결된 나라(연합) 정보를 담는 리스트
        united = []
        united.append((x,y))
        #너비 우선 탐색을 위한 큐 자료구조 정의
        q= deque()
        q.append((x,y))
        union[x][y] = index
        summary = graph[x][y] #현재 연합의 전체 인구 수
        count = 1 #현재 연합의 국가 수
        #큐가 빌 때까지 반복(BFS)
        while q:
            x, y = q.popleft()
            #현재 위치에서 4가지 방향을 확인하며 
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                #바로 옆에 있는 나라를 확인하여
                if 0 <= nx < n and 0 <= ny < n and union[nx][ny] == -1:
                    #옆에 있는 나라와 인구 차이가 L명 이상, R명 이하라면
                    if l <= abs(graph[nx][ny] - graph[x][y]) <= r:
                        q.append((nx, ny))
                        #연합에 추가
                        union[nx][ny] =index
                        summary += graph[nx][ny]
                        count += 1
                        united.append((nx, ny))
        #연합 국가끼리 인구를 분배
        for i, j in united:
            graph[i][j] = summary // count
        return count
    total_count = 0

    #더이상 인구 이동을 할 수 없을 때까지 반복
    run = True
    while run:
        union = [[-1] * n for _ in range(n)]
        index = 0
        for i in range(n):
            for j in range(n):
                if union[i][j] == -1: #해당 나라가 아직 처리되지 않았다면
                    process(i, j, index)
                    index += 1
        #모든 인구 이동이 끝난 경우
        if index == n*n:
            run = False
            break
        total_count += 1
    
    #인구 이동 횟수 출력
    print(total_count)

def study_08():
    ##블럭 이동하기
    #책과함께
    from collections import deque
    
    board = [[0,0,0,1,1], [0,0,0,1,0], [0,1,0,1,1], [1,1,0,0,1], [0,0,0,0,0]]
    
    #이동할 수 있는 확인 함수
    def get_next_pos(pos, board):
        next_pos = [] #반환 결과(이동 가능한 위치들)
        pos = list(pos) #현재 위치 정보를 리스트로 변환(집합 -> 리스트)
        pos1_x, pos1_y, pos2_x, pos2_y = pos[0][0], pos[0][1], pos[1][0], pos[1][1]
        #상하좌우 이동 하는 경우에 대해서 처리
        dx = [-1,1,0,0]
        dy = [0,0,-1,1]
        for i in range(4):
            pos1_next_x, pos1_next_y, pos2_next_x, pos2_next_y = pos1_x + dx[i], pos1_y + dy[i], pos2_x + dx[i], pos2_y + dy[i]
            #이동하고자 하는 두 칸이 모두 비어 있다면
            if board[pos1_next_x][pos1_next_y] == 0 and board[pos2_next_x][pos2_next_y] == 0:
                next_pos.append({(pos1_next_x, pos1_next_y), (pos2_next_x, pos2_next_y)})
        #현재의 로봇이 가로로 놓여 있는 경우
        if pos1_x == pos2_x:
            for i in [-1,1]: #위쪽으로 회전하거나, 아래쪽으로 회전
                if board[pos1_x+i][pos1_y] == 0 and board[pos2_x][pos2_y+i] == 0: #왼쪽 혹은 오른쪽 두 칸이 모두 비어 있다면
                    next_pos.append({(pos1_x, pos1_y),(pos1_x+i, pos1_y)})
                    next_pos.append({(pos2_x, pos2_y),(pos2_x+i, pos2_y)})
        #현재 로복이 세로로 놓여 있는 경우
        elif pos1_y == pos2_y:
            for i in [-1,1]: #왼쪽으로 회전하거나, 오른쪽으로 회전
                if board[pos1_x][pos1_y+i] == 0 and board[pos2_x][pos2_y + i] == 0:#외족 혹은 오른쪽 두 칸이 모두 비어 있다면
                    next_pos.append({(pos1_x, pos1_y),(pos1_x, pos1_y+i)})
                    next_pos.append({(pos2_x, pos2_y),(pos2_x, pos2_y+i)})
        #현재 위치에서 이동할 수 있는 위치를 반환
        return next_pos
        
        
    def solution(baord):
        n = len(board)
        #맵의 외곽에 벽을 만듬 -> 전체 1로 n+2 맵 생성
        new_board = [[1] * (n+2) for _ in range(n+2) ]
        
        for i in range(n):
            for j in range(n):
                new_board[i+1][j+1] = board[i][j]
        
        #너비 우선 탐색 BFS 수행
        q = deque()
        visited = []
        pos = {(1,1), (1,2)} #시작위치 설정
        q.append((pos, 0)) #큐에 삽입한 뒤에
        visited.append(pos) #방문처리 
        
        #큐가 빌 때까지 반복
        while q:
            pos, cost = q.popleft()
            #(n,n) 위치에 로복이 도달했다면, 최단거리이므로 반환
            if (n,n) in pos:               
                return cost
            #현재 위치에서 이동할 수 있는 위치 확인
            for next_pos in get_next_pos(pos, new_board):
                #아직 방문하지 않은 위치라면 큐에 삽입하고 방문 처리
                if next_pos not in visited:
                    q.append((next_pos, cost+1))
                    visited.append(next_pos)
    print(solution(board))

#실행   
study_08()

