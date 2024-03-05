# [PRG] JadenCase 문자열 만들기

def solution(s):
    answer = []
    s = s.split(" ")
    for word in s:
        if word:
            answer.append(word[0].upper() + word[1:].lower())
            print(word[1:])
        else:
            answer.append(word)
    return " ".join(answer)