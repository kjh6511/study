### 이어서

def study_01():
    ###바닥공사
    # 1<= n <= 1000
    n = int(input())
    d = [0] * 1001

    d[1] = 1
    d[2] = 3
    for i in range(3, n+1):
        d[i] = (d[i-1] + 2 * d[i-2]) % 796796
    print(d[n])

def study_02():
    #효율적인 화폐 구성
    n,m = map(int, input().split())
    array = []
    for i in range(n):
        array.append(int(input()))

    d = [10001] * (m+1)
    #다이나믹 프로그래밍 보텀업
    d[0] = 0
    for i in range(n): # i = 0~n-1까지 n번 반복
        for j in range(array[i], m+1):
            print("i:", i, " j: ", j, "j - array[i] :", j - array[i], "d :",d[j - array[i]] )
            if d[j - array[i]] != 10001: #(i-k)원을 만드는 방법이 존재하는 경우
                d[j] = min(d[j], d[j-array[i]]+1)
    
    if d[m] == 10001: #최종적으로 m원을 만드는 방법이 없는 경우
        print(-1)
    else:
        print(d[m])

####실행
study_02()


