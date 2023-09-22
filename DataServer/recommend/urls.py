from django.urls import path
from . import views

urlpatterns = [
    # 다른 URL 패턴 추가
    path('django/recommend/', views.recommend_view, name='recommend-view'),
]