from rest_framework import serializers
from api.models import Integration

class IntegrationSerialzer(serializers.ModelSerializer):
    class Meta:
        model = Integration
        fields = '__all__'