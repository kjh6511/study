# print 구문의 이해

#기본출력
print('Hello python!') 
print("Hello python!")
print("""Hello Python!""")
print('''Hello Python!''')

print()

#Separator 옵션 사용
#sep 문자 이어서 나옴
print('T','E','S','T', sep='')
print('2019','02','19', sep='-')
print('niceman', 'google.con', sep='@')

#end 옵션 끝을 다음문장으로
print('welcom To', end=' ')
print('the black parade', end=' ')
print('piano notes')

print()
# format 사용 [], {}, ()
print('{} and {}'.format('You', 'Me'))
print("{0} and {1} and {0}".format('You', 'Me'))
print("{a} are {b}".format(a='You', b='Me'))

#%s : 문자, %d : 정수, %f : 실수
print("%s's favorite number is %d" %('Kim', 7)) 
print("TEST1: %5d, price: %4.2f" %(776, 6534.123))
print("TEST1: {0: 5d}, price: {1:4.2f}".format(776, 6534.123))
print("TEST1: {a: 5d}, price: {b: 4.2f}".format(a=776, b=6543.123))

#이스케이프 문자
print("'you'")
print('\'you\'')
print('"you"')
print('\\you\\\n\n')
print("test")
print('\t\tyou')

