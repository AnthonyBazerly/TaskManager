import { CommonModule } from '@angular/common';
import { Component, Input, TemplateRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'custom-table',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './custom-table.component.html',
  styleUrl: './custom-table.component.css'
})
export class CustomTableComponent {
  @Input() array: any[] = [];
  @Input() selectedItems: any[] = [];
  @Input() filteredItems: any[] = [];
  @Input() route: string = '';
  @Input() templates: { [key: string]: TemplateRef<any> } = {};

  allSelected: boolean = false;

  constructor(private router: Router) {}

  ngOnChanges() {
    console.log('Array input:', this.array);
  }

  selectAll(event: any): void {
    const isChecked = event.target.checked; 
    this.allSelected = isChecked;
    this.selectedItems = isChecked ? [...this.filteredItems] : [];
  }

  onSelectionChange(item: any, event: any): void {
    if (event.target.checked) {
      this.selectedItems.push(item);
    } else {
      const index = this.selectedItems.indexOf(item);
      if (index > -1) {
        this.selectedItems.splice(index, 1);
      }
    }
    this.allSelected = this.selectedItems.length === this.filteredItems.length;
  }

  // deleteSelected(): void {
  //   this.selectedItems.forEach(item => {
  //     
  //   });
  //   this.selectedItems = [];
  //   this.allSelected = false;
  // }

  editItem(item: any): void {
    //this.router.getCurrentNavigation()?.extras.state?.item
    this.router.navigate([this.route, 'edit'], { state: { item } });
  }
}
