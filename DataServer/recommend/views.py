import pandas as pd
import json
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from sklearn.metrics.pairwise import cosine_similarity
import random

@csrf_exempt
def recommend_view(request):
    if request.method == 'POST':
        # POST 요청에서 데이터를 추출하고 처리
        user_data = {
            'age': int(request.POST['age']),
            'height': int(request.POST['height']),
            'weight': int(request.POST['weight']),
            'body_fat_percentage': int(request.POST['body_fat_percentage']),
            'Resting_BPM': int(request.POST['Resting_BPM']),
            'active_BPM': int(request.POST['active_BPM']),
        }

        recommended_data = {}  # 추천 결과 데이터
        df = pd.read_csv('dataset.csv', encoding='utf-8')

        numeric_columns = df[
            ['age', 'height', 'weight', 'body_fat_percentage', 'Resting_BPM', 'active_BPM']]

        numeric_columns = numeric_columns.dropna()

        similarities = cosine_similarity([list(user_data.values())], numeric_columns.values)

        similar_indices = similarities.argsort()[0][::-1][:50]

        random_users = random.sample(list(similar_indices), 10)  # similar_indices를 리스트로 변환

        recommended_exercises = set()
        for user_index in random_users:
            recommended_exercise = df.loc[user_index, 'Exercise']
            recommended_exercises.add(recommended_exercise)
            if len(recommended_exercises) >= 3 :
                break
        recommended_exercises = list(recommended_exercises)

        recommended_data = {
            'recommended_exercises': recommended_exercises
        }
        return JsonResponse(recommended_data)
    else:
        return JsonResponse({'error': 'Invalid request method'})