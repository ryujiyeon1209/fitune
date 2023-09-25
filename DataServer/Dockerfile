# FROM: 이미지 지정
FROM python:3.11

# 작업 디렉토리를 설정합니다.
WORKDIR /app

# 필요한 패키지를 직접 설치합니다.
RUN pip install --upgrade pip

COPY requirements.txt /app/requirements.txt
RUN pip install sklearn
RUN pip install -r /app/requirements.txt

# 소스 코드를 복사합니다.
COPY . /app/

# Django 애플리케이션을 실행합니다.
CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]
