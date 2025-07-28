import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';
import { environment } from '../../environment';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-chats',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './view-chats.component.html',
  styleUrl: './view-chats.component.css'
})
export class ViewChatsComponent {
  chats: any[] = [];
  privateChats: any[] = [];
  groupChats: any[] = [];
  chatRooms: any[] = [];
  selectedChats: any[] = [];
  filteredChats: any[] = [];

  constructor(private router: Router, private http: HttpClient, private authService: AuthService) { }

  ngOnInit() {
    this.chats = this.authService.chats;
    if (this.chats.length === 0) {
      this.loadChats();
    }
    
    this.filteredChats = this.chats;
    this.privateChats = this.sortChats(this.chats.filter(chat => chat.chatType === 'private'));
    this.groupChats = this.sortChats(this.chats.filter(chat => chat.chatType === 'group'));
    this.chatRooms = this.sortChats(this.chats.filter(chat => chat.chatType === 'chatroom'));
  }

  loadChats() {
    this.http.get<any[]>(`${environment.apiUrl}/api/chats`, { withCredentials: true }).subscribe({
      next: res => {
        this.chats = this.sortChats(res);
        this.authService.chats = this.chats;
      },
      error: err => {
        console.error("Failed to load chats", err);
      }
    });
  }

  sortChats(chats: any[]): any[] {
    return chats.sort((a, b) => {
      return new Date(b.chatLastUpdate.replace(' ', 'T')).getTime() - new Date(a.chatLastUpdate.replace(' ', 'T')).getTime();
    });
  }

  createChat(): void {
    this.router.navigate(['/create-chat']);
  }

  enterChat(chat: any): void {
    this.router.navigate(['/view-chat', chat.chatId]);
  }
}
