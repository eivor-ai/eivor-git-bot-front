from django.db import models
from django.conf import settings
from django.contrib.auth.models import User


class Integration(models.Model):
    app_name = models.CharField(max_length=100)
    secret = models.CharField(max_length=64)
    oauth_token = models.CharField(max_length=100)
    bot_username = models.CharField(max_length=32)
    server_url = models.CharField(max_length=200)
    user = models.ForeignKey(settings.AUTH_USER_MODEL,
                             on_delete=models.CASCADE)

    class Meta:
        db_table = 'integration'


class Settings(models.Model):
    mr_matcher = models.CharField(max_length=150, blank=True)
    mr_failed_content = models.CharField(max_length=150, blank=True)
    mr_accepted_content = models.CharField(max_length=150, blank=True)
    mr_default_assignee = models.CharField(max_length=150, blank=True)
    integration = models.OneToOneField(Integration, on_delete=models.CASCADE)

    class Meta:
        db_table = 'preferences'
