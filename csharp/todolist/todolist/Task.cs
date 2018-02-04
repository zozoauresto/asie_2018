using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace todolist
{
    class Task : INotifyPropertyChanged
    {
        public static int TOTAL_NBR_TASK = 0;

        private string title;

        public event PropertyChangedEventHandler PropertyChanged;

        public string Title { get { return title; }
            set
            {
                if (title != value)
                {
                    title = value;
                    OnPropertyChanged("Title");
                }
            }
        }

        private string desc;
        public string Desc { get { return desc; } set {
                if (desc != value)
                {
                    desc = value;
                    OnPropertyChanged("Desc");
                }
            }
        }

        private string date;
        public string Date { get { return date; } set {
                if (date != value)
                {
                    date = value;
                    OnPropertyChanged("Date");
                }
            }
        }

        public int Id { get; set; }

        public bool Checked { get; set; }

        public Task(string task, string desc, string date)
        {
            Id = TOTAL_NBR_TASK;
            ++TOTAL_NBR_TASK;
            Title = task;
            Desc = desc;
            Date = date;
            Checked = false;
        }

        private void OnPropertyChanged(string prop)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(prop));
            }
        }
    }
}
