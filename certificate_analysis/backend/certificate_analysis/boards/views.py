from django.shortcuts import render, get_object_or_404
from django.http import Http404
from .models import Board

from django.http import HttpResponse

# Create your views here.
from boards.models import Board


def home(request):

    boards = Board.objects.all()
    return render(request, 'index.html', {'boards': boards})


def board_topics(req, pk):
    board = get_object_or_404(Board, pk=pk)
    return render(req, 'topics.html', {'board': board})

