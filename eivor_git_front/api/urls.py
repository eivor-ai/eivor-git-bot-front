from rest_framework import routers
from .api import IntegrationViewSet

router = routers.DefaultRouter()
router.register('api/integrations', IntegrationViewSet, 'integrations')

urlpatterns = router.urls
