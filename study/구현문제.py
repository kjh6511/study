###구현문제
#완전탐색, 시물레이션
#완전탐색 : 모든 경우의 수를 빠짐없이 다 계산하는 해결 방법
# - 반복문, 재귀함수를 적절히 사용하고 예외 케이스를 모두 확인
# - DFS/BFS 사용
#시물레이션 : 문제에서 제시하는 논리나 동작 과정을 그대로 코드로 옮겨야 하는 유형을 의미
#원소를 나열하는 모든 경우의 수를 고려해야 하는 상황에서는 순열이나 조합 라이브러리를 사용 itertools

def study_01():
    #럭키 스트레이트
    #왼쪽 수 합과 오른쪽 수 합이 동일한지

    n = input()
    
    #가운데 값
    mid = len(n)/2
    
    #계산
    leftSum = 0
    rightSum = 0
    for i in range(len(n)):
        if i < mid:
            leftSum += int(n[i])
        else:
            rightSum += int(n[i])

    result = "READY"
    if leftSum == rightSum:
        result = "LUCKY"
    
    print(result)    
    
def study_02():
    #문자열 재정렬
    
    n = input()
    
    eng = []
    num = 0
    
    for i in range(len(n)):
        if n[i].isalpha():
           eng.append(n[i])
        else:
            num += int(n[i])
    
    eng.sort()
    
    result = ''
    for e in eng:
        result += e
    
    if num != 0:
        result += str(num)
    
    print(result)
    
    
    ### 책풀이
    
    r = []
    value = 0
    
    for x in n:
        if x.isalpha():
            r.append(x)
        else:
            value += int(x)
    
    r.sort()
    
    if value != 0:
        r.append(str(value))
        
    print(''.join(result))

def study_03():
    #문자열 압축
    #완전탐색
    ### 아래는 앞에서 부터 각 단위로 자르지 않고 한칸씩 이동하며 자름 
    
    
    n = str(input())
    arr = [] * (len(n) + 1) #각인덱스는 잘랐을때 길이 결과값
    arr.append((len(n),0))
    
    for i in range(1, len(n)+1):
        curr = 0
        check = 0
        result = 0
        while curr <= len(n) : #True 상황을 넣음
            currStr = n[curr: curr + i]
            aftrStr = n[curr + i : (curr + i) + i]

            if len(currStr) != len(aftrStr):
            # 마지막 확인
                if check != 0:
                    result += (i+1)
                else:    
                    result += (len(n[curr : len(n)]))
                curr = len(n)
            else:
                if currStr  == aftrStr:
                #같으면
                    curr = curr + i
                    check += 1
                else:
                    if check != 0:
                        #비교 카운터가 증가했으면 
                        result += (i+1)
                        curr += i
                    else:
                        result += 1
                        curr += 1
                    check = 0

        arr.append((result, i))
    
    print(" arr :", arr)
    arr  = sorted(arr, key=lambda x : x[0]) ## 그냥 리스트로 해도됨 
    print(arr[0][0])
    

    #### 단위로 자른걸로 다시 풀기
    
    resultArr = [] * ((len(n)//2)+1) ##n개의 반까지만 서로 비교가능
    for x in range(1, (len(n)//2)+1):
        curr = 0
        result = 0
        check = 0
        while curr <= len(n):
            currStr = n[curr : curr + x]
            aftStr = n[curr + x : (curr + x) + x]

            if len(currStr) != len(aftStr): #마지막부분
                if check != 0:
                    result += x+1
                else:
                    result += (len(n[curr : len(n)]))
                curr = len(n)
            else:
                if currStr == aftStr:
                    check += 1
                else:
                    result += x
                    if check != 0:
                        result += 1 
                    check = 0
                curr += x
        resultArr.append(result)
    resultArr.sort()
    print(resultArr[0])
     
    ##책풀이
    def solutions(s):
        answer = len(s)
        #1개 단위부터 압축 단위를늘려가며 확인
        for step in range(1, len(s) // 2 + 1): 
            compressed = ""
            prev = s[0:step] #앞에서부터 step만큼의 문자열 추출
            count = 1
            #단위(step)크기만큼 증가시키며 이전 문자열과 비교
            for j in range(step, len(s), step):
                #이전 상태와 동일하다면 압출 횟수 증가
                if prev == s[j:j+step]:
                    count += 1
                #다른 문자열이 나왔다면(더 이상 압축하지 못하는 경우라면)
                else: 
                    compressed += str(count) + prev if count >= 2 else prev
                    prev = s[j : j+step] #다시 상태 초기화
                    count = 1
            #남아 있는 문자열에 대해서 처리
            compressed += str(count) + prev if count >= 2 else prev # a if 조건 else b (조건이면 a 아니면 b)
            #만들어지는 압축 문자열이 가장 짧은 것이 정답
            answer = min(answer, len(compressed))
        return answer
    print(solutions(n))

def study_04():
    #자물쇠와 열쇠
    key = [[0,0,0], [1,0,0], [0,1,1]]
    lock = [[1,1,1], [1,1,0], [1,0,1]]
    
    keyLen = len(key)
    lockLen = len(lock)
    result = 'flase'
    currKey = key
    for i in range(1):
        #키 회전(오른쪽 90도 회전)
        moveKey = [[0] * keyLen for _ in range(keyLen)] ###초기화 주의
        for j in range(keyLen):
            for k in range(keyLen):
                moveKey[k][(keyLen-1) - j] = currKey[j][k]
        currKey = moveKey
        
        #key를 행렬 한칸씩 이동하면서 lock과 비교
        breaker = False
        for j in range(lockLen):
            for k in range(lockLen):
                keyLock = [[1] * lockLen for _ in range(lockLen)] #이동한 key를 넣는 곳 (1,0 반전)
                for a in range(keyLen):
                    for b in range(keyLen):
                        if a+j >= lockLen or b+k >= lockLen:
                            break 
                        else:
                            vlaue = 0 if currKey[a][b] == 1 else 1 #(1,0 반전)
                            keyLock[a+j][b+k] = vlaue         
                if keyLock == lock:
                    result = "true"
                    breaker = True
                    break
            if breaker == True:
                break
        
    print(result)
    
    
    ### 책 풀이
    
    #2차원 리스트 90도 회전
    def rorate_a_matrix_by_90_degree(a):
        n = len(a) # 행 길이
        m = len(a[0]) #열 길이 
        result = [[0] * n for _ in range(m)] #결과 리스트
        for i in range(n):
            for j in range(m):
                result[j][n-i-1] = a[i][j]
        return result
    
    #자물쇠의 중간 부분이 모두 1인지 확인
    def check(new_lock):
        lock_length = len(new_lock) // 3
        for i in range(lock_length, lock_length * 2):
            for j in range(lock_length, lock_length * 2):
                if new_lock[i][j] != 1:
                    return False
        return True
    
    def solution(key, lock):
        n = len(lock)
        m = len(key)
        #자물쇠의 크기를 기존의 3배로 변환
        new_lock = [[0] * (n*3) for _ in range(n*3)]
        #새로운 자물쇠의 중앙 부분에 기존의 자물쇠 넣기
        for i in range(n):
            for j in range(n):
                new_lock[i+n][j+n] = lock[i][j]
        
        #4가지 방향에 대해서 확인
        for rotation in range(4):
            key = rorate_a_matrix_by_90_degree(key) #열쇠 회전
            
            for x in range(n*2):
                for y in range(n*2):
                    #자물쇠에 열쇠를 끼워 넣기
                    for i in range(m):
                        for j in range(m):
                            new_lock[x+i][y+j] += key[i][j]
                    #새로운 자물쇠에 열쇠가 정확히 들어맞는지 검사
                    if check(new_lock) == True:
                        return True
                    #자물쇠에서 열쇠를 다시 빼기
                    for i in range(m):
                        for j in range(m):
                            new_lock[x+i][y+j] -= key[i][j]
        return False
    print(solution(key,lock))    
                
def study_05():
    
    #방향 회전하기 
    def direction_change(d, c): #방향 잡기, (현재방향, 회전방향)
        right = [[0,1],[1,0],[0,-1],[-1,0]]
        left = [[-1,0],[0,-1],[1,0],[0,1]]
        if c == "D": #오른쪽으로 90  
            curr_index = right.index(d)
            curr_index += 1
            if curr_index >= 4:
                curr_index = 0
            return right[curr_index]
        else : #왼쪽으로 90
            curr_index = left.index(d)    
            curr_index += 1
            if curr_index >= 4:
                curr_index = 0
            return left[curr_index]
        
    #크기 
    #n = int(input())
    n = 10
    #0으로 초기화
    board = [[0] * n for _ in range(n)]
    
    #사과 갯수
    # k = int(input())
    k = 4
    # for i in range(k):
    #     a, b = map(int,input().split())
        # board[a-1][b-1] = 1 #1이면 사과가 있음
    apple=[[1,2],[1,3],[1,4],[1,5]]
    for i in apple:
        board[i[0]-1][i[1]-1] = 1
    
    
    #이동경로를 저장
    # moveCount = int(input())
    moveCount = 3
    move = []
    # for i in range(moveCount):
    #     a, b = map(str, input().split())  ########
    #     move.append((int(a),b))
    move = [[8,"D"],[10,"D"],[11,"D"],[13,"L"]]
        
    snake_h = [0,0] #현재 뱀의 위치, 머리
    snake_t = [] #몸+꼬리
    dirc = [0,1] # 현재 방향
    time = 0
    
    breaker = False
    for i in move:
        #현재 위치에서 해당 값만큼 이동하기
        print("==============================")
        print("이동 : ", i)
        for go in range(i[0]):
            #머리이동
            snake_h[0] = snake_h[0] + dirc[0]
            snake_h[1] = snake_h[1] + dirc[1]
            print("---------------- 이동 번수 : ", go)
            print("이동한 머리 : ", snake_h)
            #벽과 만나는지 확인
            if snake_h[0] >= n or snake_h[1] >=n or snake_h[0] < 0 or snake_h[1] < 0:
                #벽과 만나면 게임 종료
                time += 1
                breaker = True
                break
            
            print("현재꼬리 : ", snake_t)
            # 꼬리와 만나는지 확인
            if len(snake_t) != 0 and snake_t[0] == snake_h:
                print("만날시 머리 : ",snake_h)
                print("만날시 꼬리 : ",snake_t)
                #꼬리와 만나면 게임 종료 
                time += 1
                breaker = True
                break
            
            print("사과확인  : ", board[snake_h[0]][snake_h[1]])    
            #사과가 있는지 확인
            if board[snake_h[0]][snake_h[1]] != 1:
                #사과가 없고 꼬리가 있으면 꼬리 이동
                if len(snake_t) != 0:
                    #마지막 제거
                    del snake_t[0]
                    #현재머리추가
                    snake_t.append([snake_h[0],snake_h[1]])
            else:
                #사과가 있으면 사과자리 0
                board[snake_h[0]][snake_h[1]] = 0
                #현재머리추가
                snake_t.append([snake_h[0],snake_h[1]])   
            print("변화꼬리 : ", snake_t)    

            time += 1
            print("현재시간 : ", time)
        
        #방향 전환
        r = direction_change(dirc,i[1])
        dirc[0] = r[0]
        dirc[1] = r[1]
                
        #브레이크
        if breaker == True:
            break        
    
    print(time)       
    print("=============================")
    
    ## 책풀이
    data = [[0] * (n+1) for _ in range(n+1)] #맵정보
    info = [] #방향정보
    
    #맵정보 (사과를 1로 표시)
    for i in apple:
        data[i[0]][i[1]] = 1
    
    #방향 회전 정보 입력
    l = moveCount
    for i in move:
        info.append((i[0], i[1]))
    
    #처음에는 오늘쪽을 보고 있으므로 (동, 남, 서, 북) 
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    
    def turn(direction, c):
        if c == "L": #왼쪽으로 회전
            direction = (direction - 1) % 4
        else:
            direction = (direction + 1) % 4
        return direction
    
    def simulate():
        x,y = 1,1 #뱀의 머리 위치
        data[x][y] = 2 #뱀이 존재 하는 위치는 2로 표시
        direction = 0 #처음에는 동쪽을 보고 있음
        t = 0 #시작한 뒤에 지난 초
        index = 0 # 다음에 회전할 정보
        q = [(x,y)] #뱀이 차지하고 있는 위치정보 (꼬리가 앞쪽)
        while True:
            nx = x + dx[direction]
            ny = y + dy[direction]
            
            #맵 범위 안에 있고, 뱀의 몸통이 없는 위치라면
            if 1 <= nx and nx <= n and 1<=  ny and ny <= n and data[nx][ny] != 2:
                #사과가 없다면 이동 후에 꼬리 제거
                if data[nx][ny] == 0:
                    data[nx][ny] = 2
                    q.append((nx,ny))
                    px , py = q.pop(0)
                    data[px][py] = 0
                #사과가 있다면 이동 후에 꼬리 그대로 두기
                if data[nx][ny] == 1:
                    data[nx][ny] = 2
                    q.append((nx, ny))
            #벽이나 몸통과 부딪혔다면
            else:
                t += 1
                break
            x,y = nx, ny #다음 위치로 머리를 이동
            t += 1
            if index < 1 and t == info[index][0]: #회전할 시간인 경우 회전
                direction = turn(direction, info[index][1])
                index += 1
        return t
    print(simulate())    

def study_06():
    
    def check_delete_cloth(x,y, answer,check, dirc):
        if [x+1, y,1] not in answer and [x-1, y, 1] not in answer:
            check =False
            print("1 :", check)
            if dirc == 'l' and [x, y-1, 0] not in answer:
                check = False
            if dirc == 'r' and [x+1, y-1, 0] not in answer:
                check = False   
        return check
    
    def check_delete_pillar(x,y,answer):
        check = True
        if [x-1, y+1, 1] not in answer or [x+1, y+1, 1] not in answer:
            check = False
        return check
    
    def solution(n, build_frame):
        answer = []

        curr = [0,0,0,0] #현재위치, 아래쪽 나무 유무, 왼쪽 보 유무
        
        #차례대로 실행
        for f in build_frame:
            x = f[0] #x위치
            y = f[1] #y위치
            a = f[2] #기둥/보
            b = f[3] #삭제/설치   
            # 기둥 검사
            if a == 0: 
                #설치
                if b == 1:
                    if y + 1 <= n: #n이상 불가
                        if y == 0 or [x, y-1, 0] in answer or [x-1, y, 1] in answer or [x, y, 1] in answer:
                            #기둥설치
                            answer.append([x,y,0])
                #제거
                else:
                    check = True
                    if [x, y+1, 0] in answer:#설치된 위에 기둥기준
                       check = check_delete_pillar(x,y+1,0,check)
                    if [x-1, y+1, 1] in answer: #설치된 왼쪽 보 기준
                        check = check_delete_cloth(x-1, y+1, answer,check,"l")
                    if [x+1, y+1, 1] in answer: #설치된 오른쪽 보 기준
                        check = check_delete_cloth(x+1, y+1, answer,check,"r")
                    if check == True:
                        answer.remove([x,y,a])
                        
            #보 검사
            else:
                #설치
                if b == 1:
                    if x + 1 <= n:
                        if [x, y-1, 0] in answer or [x+1, y, 1] in answer or [x+1, y-1, 0] in answer:
                            #보 설치
                            answer.append([x,y,1])    
                #제거
                else:
                    check = True
                    if [x+1,y,0] in answer: #오른쪽 기둥
                        check = check_delete_pillar(x+1,y,answer,check)
                    if [x,y,0] in answer: #같은자리(왼쪽기준) 기둥
                        check = check_delete_pillar(x,y,answer,check)
                    if [x-1, y ,1] in answer and [x,y-1,0] not in answer and [x-1, y-1,0]: #왼쪽에 보가 있다면
                        check = False
                    if [x+1, y ,1] in answer and [x+1,y-1,0] not in answer and [x, y-1,0]: #오른쪽에 보가 있다면
                        check = False
                    if check == True:
                        answer.remove([x,y,a])
        
        #순서대로 정렬 
        answer.sort(key=lambda x: (x[0],x[1],x[2])) 
        return answer    
    
    
    n = 5
    build_frame = [[0,0,0,1], [2,0,0,1], [4,0,0,1], [0,1,1,1],[1,1,1,1],[2,1,1,1], [3,1,1,1], [2,0,0,0], [1,1,1,0], [2,2,0,1]] 
    
    print(solution(n,build_frame))
    
    ##책풀이
    #시물레이션 문제 
    
    #현재 설치된 구조물이 '가능한' 구조물인지 확인하는 함수
    def possible(answer):
        for x,y, stuff in answer:
            if stuff == 0: # 설치된 것이 '기둥'인 경우
                #'바닥 위' 혹은 '보의 한쪽 끝부분 위' 혹은 '다른 기둥 위' 라면 정상
                if y == 0 or [x-1,y,1] in answer or [x,y,1] in answer or [x,y-1,0] in answer:
                    continue
                return False #아니라면 거짓
            elif stuff == 1: #설치된 것이 '보'인 경우
                #'한쪽 끝부분이 기둥 위' 혹은 '양쪽 끝부분이 다른 보와 동시에 연결'이라면 정상
                if [x,y-1,0] in answer or [x+1, y-1, 0] in answer or ([x-1,y,1] in answer and [x+1,y,1] in answer):
                    continue
                return False #아니라면 거짓 반환
        return True
    
    def solution02(n, build_frame):
        answer = []
        for frame in build_frame: #작업의 개수는 최대 1000개
            x, y, stuff, operate = frame
            if operate == 0: #삭제하는 경우
                answer.remove([x,y,stuff]) #일단 삭제를 해본 뒤에 
                if not possible(answer): #가능한 구조물인지 확인
                    answer.append([x,y,stuff]) #가능한 구조물이 아니라면 다시 설치
            if operate == 1: #설치하는 경우
                answer.append([x,y,stuff]) #일단 설치를 해본 뒤에
                if not possible(answer): #가능한 구조물인지 확인
                    answer.remove([x,y,stuff]) #가능한 구조물이 아니라면 다시 제거
        return sorted(answer) #정렬된 결과를 반환
                
    print(solution02(n,build_frame))

def study_07():
    ##치킨 배달, 책풀이와 같이

    #조합 라이브러리
    from itertools import combinations
        
    n , m = map(int,input().split())
    
    data = [[0,0,1,0,0],[0,0,2,0,1],[0,1,2,0,0],[0,0,1,0,0],[0,0,0,0,2]]
    
    # for i in range(n):
    #     info = list(map(int,input().split()))
    #     data.append(info)

    house = []
    chicken = []
    
    # 집과 치킨 데이터 저장
    for j in range(n):
        for k in range(n):
            if data[j][k] == 1:
                house.append([j,k])
            elif data[j][k] == 2:
                chicken.append([j,k])   # 3번째 최소가게 카운트 
   
    #모든 치킨집 중에서 m개의 치킨집을 뽑는 조합 계산
    cadidates = list(combinations(chicken, m))
    
    #치킨 거리의 합을 계산하는 함수
    def get_sum(candidate):
        result = 0
        #모든 집에 대하여
        for hx, hy in house:
            #가장 가까운 집 찾기
            temp = 1e9
            for cx, cy in candidate:
                temp = min(temp, abs(hx -cx) + abs(hy-cy))
            #가장 가까운 치킨집까지의 거리 더하기
            result += temp
        return result 
    
    #치킨 거리의 합의 초소를 찾아 출력
    result = 1e9
    for cadidate in cadidates:
        result = min(result, get_sum(cadidate))
    
    print(result)

def study_08():
    ##외벽점검 , 책풀이와 함께
    #모든 경우의 수 구하는 라이브러리 
    from itertools import permutations
    
    def solution(n, weak, dist):
        #길이를 2배로 늘려서 '원형'을 일자 형태로 변형
        length = len(weak)
        for i in range(length):
            weak.append(weak[i]+n)
        answer = len(dist) + 1 #투입할 친구 수의 최솟값을 찾아야 하므로 len(dist)+1로 초기화
        #0부터 length -1 까지의 위치를 각각 시작점으로 설정
        for start in range(length):
            #친구를 나열하는 모든 경우의 수 각각에 대햐여 확인
            for friends in list(permutations(dist,len(dist))):
                count = 1 #투입한 친구 수
                #해당 친구가 점검할 수 있는 마지막 위치
                position = weak[start] + friends[count-1]
                #시작점부터 모든 취약 지점을 확인
                for index in range(start, start+length):
                    #점검할 수 있는 위치를 벗어나는 경우
                    if position < weak[index]:
                        count += 1 #새로운 친구를 투입
                        if count > len(dist): #더 투입이 불가능하다면 종료
                            break
                        position = weak[index] + friends[count -1]
            answer = min(answer, count) #최솟값
            
        if answer > len(dist):
            return -1
        return answer
    
    
    n = 12
    weak = [1,5,6,10]
    dist = [1,2,3,4]
    print(solution(n,weak,dist))


    

### 실행
study_08()    