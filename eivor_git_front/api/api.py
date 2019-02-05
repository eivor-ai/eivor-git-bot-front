from rest_framework import viewsets, permissions

from api.models import Integration

from .serializers import IntegrationSerialzer


class IntegrationViewSet(viewsets.ModelViewSet):
    queryset = Integration.objects.all()
    serializer_class = IntegrationSerialzer
    permission_classes = (permissions.AllowAny,)
