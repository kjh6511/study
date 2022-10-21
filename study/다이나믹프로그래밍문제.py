##다이나믹프로그래밍 문제
#다이나믹 프로그래밍 : 해결된 부분을 메모리에 기록하여 한번 계산한 답은 다시는 계산하지 않도록, 점화식(인접한 항들의 관계식)
# - 피보나치 수열, 그래프 이론, 플로이드 워셜 알고리즘 등 활용
#피보나치 수열 : 대표적인 다이나믹 프로그래밍
#탑다운과 보텀업 
# - 탑다운 : 재귀함수를 이용하여 큰 문제르 해결하기 위해 작은 문제를 호출하는 방식
# - 보텀업 : 단순히 반복문을 이용하여 작은 문제를 먼저 해결하고 해결된 작은 문제를 모아 큰 문제를 해결

from sys import api_version


def study_01():
    ###금광
    
    t = int(input()) #몇번 진행인지
    stages= []
    for _ in range(t):
        n , m = map(int, input().split())
        map_data = list(map(int, input().split()))
        stages.append( (n, m, map_data) )    

    for n, m, map_data in stages:
        
        #2차원 테이블 초기화
        dp = []
        index = 0
        for i in range(n):
            dp.append(map_data[index:index+m])
            index += m
            
        #다이나믹 프로그래밍
        for j in range(1,m):
            for i in range(n):
                #왼쪽 위에서 오는 경우
                if i == 0:
                    left_up = 0
                else:
                    left_up = dp[i-1][j-1]
                #왼쪽 아래에서 오는 경우
                if i == n-1:
                    left_down = 0
                else:
                    left_down = dp[i+1][j-1]
                #왼쪽에서 오는 경우
                left = dp[i][j-1]
                dp[i][j] = dp[i][j] + max(left_up, left_down, left)
        result = 0
        for i in range(n):
            result = max(result, dp[i][m-1])
        
        print(result)
    

def study_02():
    ### 정수 삼각형
    n = int(input())
    data = []
    result = []
    
    for _ in range(n):
        data_value = list(map(int, input().split()))
        data.append(data_value)
        result.append([0 for _ in range(len(data_value))])
    
    result[0][0] = data[0][0]
    for i in range(n-1): #마지막줄은 계산이 끝난 상태라 전까지만 순환
        for j in range(i+1):
            vlaue = result[i][j] 
            result[i+1][j] = max(result[i+1][j] , data[i+1][j] + vlaue)
            result[i+1][j+1] = max(result[i+1][j+1]  , data[i+1][j+1] + vlaue)
            
    max_value = max(result[n-1])
    print(max_value)      
    
    ## 책풀이
    dp = []           
    
    for _ in range(n):
        dp.append(list(map(int, input().split())))
    
    for i in range(1,n):#두번째 줄부터
        for j in range(i+1):
            #왼쪽 위에서 내려오는 경우
            if j == 0:
                up_left = 0
            else:
                up_left = dp[i-1][j-1]
            #바로 위에서 내려오는 경우
            if j==i:
                up = 0
            else:
                up = dp[i-1][j]
            #최대 합을 저장
            dp[i][j] = dp[i][j] + max(up_left, up)
    
    print(max(dp[n-1]))
                

def study_03():
    ###퇴사
    ## 책과 함께
    #거꾸로 확인
    n = 10
    data = [[5,50],[4,40],[3,30],[2,20],[1,10],[1,10],[2,20],[3,30],[4,40],[5,50]]
    max_value =0 
    
    result = [0 for _ in range(n+1)]
    
    #각 더 하기
    for i in range(n-1, -1, -1): #뒤에서부터 거꾸로
        time = data[i][0] + i
        
        #상담이 기간안에 끝나는 경우
        if time <= n:
            #점화식에 맞게, 현재까지의 최고 이익 계산
            result[i] = max(data[i][1] + result[time], max_value)
            max_value = result[i]
        #상담이 기간을 벗어나는 경우
        else:
            result[i] = max_value
        
        
            
    print(result)
    print(max_value)

def study_04():
    ###병사 배치하기
    n = 10
    data = [15, 11, 4, 8, 5, 2, 4,3,6,1]
    
    remove_count = 0
    
    for i in range(n-1):
        if data[i] < data[i+1]:
            max_value = max(data[i], data[i+1])            
            data[i] = max_value
            data[i+1] = max_value
            remove_count += 1
    
    print(remove_count)    
    
    ##책풀이
    #가장 긴 증가하는 부분 수열 문제 : 하나의 수열이 주어졌을 떄 값들이 증가하는 형태의 가장 긴 부분 수열을 찾는 문제 
    # ㄴ가장 갯수가 많이 되는 수열
    array = [15, 11, 4, 8, 5, 2, 4,3,6,1] 
    array.reverse()
    
    dp = [1] * n
    
    #가장 긴 증가하는 부분 수열(LIS) 알고리즘 수행
    for i in range(1,n):
        for j in range(0,i):
            if array[j]  < array[i]:
                dp[i] = max(dp[i], dp[j]+1)
                
    print(dp)
    print(n - max(dp))
    
def study_05():
    ### 못생긴 수
    #오직 2,3,5만을 가지는 수
    
    n = 23
    
    point = [2,3,5]
    result = [0] * 1001
    result[1] = 1
    for i in range(1, 501):
        for val in point:
            if result[i] != 0 and i*val <= 1000:
                result[i*val] = i*val
    
    #중복 제거
    while 0 in result:
        result.remove(0)
    
    print(result)
    print(result[n-1])       
    
    ##책풀이 
    ugly = [0] * n #n까지만 확인하니깐
    ugly[0] = 1
    
    #2배, 3배, 5배를 위한 인덱스
    i2 = i3 = i5 = 0
    #처음에 곱셈값을 초기화
    next2, next3, next5 = 2,3,5
    
    #1부터 n까지 못생긴 수를 찾기
    for l in range(1,n):
        #가능한 곱셈 결과 중에서 가장 작은 수를 선택
        ugly[l] = min(next2, next3, next5)
        #인덱스에 따라 곱셈 결과를 증가
        if ugly[l] == next2:
            i2 += 1
            next2 = ugly[i2] * 2
        if ugly[l] == next3:
            i3 += 1
            next3 = ugly[i3] * 3
        if ugly[l] == next5:
            i5 += 1
            next5 = ugly[i5] * 5
    
    print(ugly)
    print(ugly[n-1])
    
def study_06():
    ###편집 거리
    #2차원 다이나믹
    
    #최소 편집 거리 계산을 위한 다이나믹 프로그래밍
    def edit_dist(str1, str2):
        n = len(str1)
        m = len(str2)
        
        #다이나믹 프로그래밍을 위한 2차원 dp 테이블 초기화
        dp = [[0] * (m+1) for _ in range(n+1)]
        
        #dp 테이블 초기 설정
        for i in range(1, n+1):
            dp[i][0] = i
        for j in range(1, m+1):
            dp[0][j] = j
        
        #최소 편집 거리 계산
        for i in range(1, n+1):
            for j in range(1, m+1):
                #문자가 같다면, 왼쪽 위에 해당하는 수를 그대로 대입
                if str1[i-1] == str2[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                #문자가 다르다면, 3가지 경우 중에서 최솟값 찾기
                else: #삽입(왼쪽), 삭제(위쪽), 교체(왼쪽 위) 중에서 최소 비용을 찾아 대입
                    dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])
        
        return dp[n][m]
    
    print(edit_dist("sunday", "saturday"))
    
    
study_06()