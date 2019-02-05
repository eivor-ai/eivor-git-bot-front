from rest_framework import permissions, viewsets

from api.models import Integration

from .serializers import IntegrationSerialzer


class IntegrationViewSet(viewsets.ModelViewSet):
    queryset = Integration.objects.all()
    permission_classes = [
        permissions.AllowAny  # Auth?
    ]
    serializer_class = IntegrationSerialzer
