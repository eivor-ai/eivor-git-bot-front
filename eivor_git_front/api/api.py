from rest_framework import viewsets, permissions

from api.models import Integration

from .serializers import IntegrationSerialzer


class IntegrationViewSet(viewsets.ModelViewSet):
    queryset = Integration.objects.all()
    serializer_class = IntegrationSerialzer
    permission_classes = [
        permissions.IsAuthenticated
    ]

    def get_queryset(self):
        return self.request.user.integrations.all()

    def perform_create(self, serializer):
        serializer.save(user=self.request.user)
