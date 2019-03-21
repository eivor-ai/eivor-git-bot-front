from django.urls import path, include
from .api import LoginApi, UserApi
from knox import views as knox_views

urlpatterns = [
    path('api/auth', include('knox.urls')),
    path('api/auth/login', LoginApi.as_view()),
    path('api/auth/logout', knox_views.LogoutView.as_view()),

    path('api/account/user', UserApi.as_view())
]
