
#좌표이동
def study_01():
    n = int(input()) #n입력받기
    x,y = 1, 1
    plans = input().split()
    
    #L,R,U,D에 따른 이동 방향
    dx = [0,0,-1,1]
    dy = [-1,1,0,0]
    move_types = ['L','R','U','D']
    
    for plan in plans:
        for i in range(len(move_types)): #이동후 좌표
            if plan == move_types[i]:
                nx = x + dx[i]
                ny = y + dy[i]
        #공간을 벗어나는 경우 무시
        if nx<1 or ny <1 or nx > n or ny > n:
            continue
        #이동수행
        x,y = nx, ny
    print(x,y)            

#시간 완전탐색
def study_02():
    h = int(input())
    
    count =0
    for i in range(h+1):
        for j in range(60):
            for k in range(60):
                if '3' in str(i) + str(j) + str(k):
                    count += 1
    
    print(count)                

#왕실의 나이트
def study_03():
    input_data = input()
    row = int(input_data[1])
    colum = int(ord(input_data[0])) - int(ord('a')) + 1
    steps = [(-2,-1), (-1,-2), (1,-2), (2,-1), (2,1), (1,2), (-1,2), (-2,1)]
    result =0 
    for step in steps:
        #이동하고자 하는 위치 확인
        next_row = row +step[0]
        next_colum = colum + step[1]
        #해당 위치로 이동이 가능하면 카운트
        if next_row >= 1 and next_row <= 8 and next_colum >= 1 and next_colum <= 8:
            result += 1
    print(result)        

#게임 개발
def study_04():
    n, m = map(int, input().split())
    d = [[0] * m for _ in range(n)] #[0 X n]의 m개 매트릭스 생성
    x, y, dire = map(int, input().split())
    d[x][y] = 1 #현재 좌표 방문 처리
    
    #전체 맵정보 입력
    array = []
    for i in range(n):
        array.append(list(map(int, input().split())))
    
    #북,동,남,서 방향 정의
    dx = [-1,0,1,0]
    dy = [0,1,0,-1]
    
    #왼쪽으로 회전
    def turn_left():
        # global dire
        nonlocal dire #def안에 def가 있을경우 nonlocal로 전역변수 
        dire -= 1
        if dire == -1:
            dire = 3    
        
    #시물레이션 시작
    count = 1
    turn_time = 0
    while True:
        #왼쪽으로 회전
        turn_left() 
        nx = x + dx[dire]
        ny = y + dy[dire]
        #회전이후 정며에 가보지 않은 칸이 존재하는 경우 이동
        if d[nx][ny] == 0 and array[nx][ny] == 0:
            d[nx][ny] = 1
            x = nx
            y = ny
            count += 1
            turn_time = 0
            continue
        #회전한 이후 정며에 가보지 않은 카이 없거나 바다인 경우
        else:
            turn_time += 1
        #네 방향 모두 갈 수 없는 경우
        if turn_time == 4:
            nx = x - dx[dire]
            ny = y = dy[dire]
            #뒤로 갈 수 있다면 이동하기
            if array[nx][ny] == 0:
                x = nx
                y = ny
            #뒤가 바다로 막혀 있는경우
            else:
                break
            turn_time = 0
    print(count)                          

####실행
study_04()